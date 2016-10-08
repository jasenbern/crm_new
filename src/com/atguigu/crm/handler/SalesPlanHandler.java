package com.atguigu.crm.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesPlanService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/plan")
@Controller
public class SalesPlanHandler {

	@Autowired
	private SalesPlanService salesPlanService;

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Map<String, Object> map) {

		SalesChance chance = salesPlanService.getDetails(id);

		map.put("chance", chance);

		return "plan/detail";
	}

	@ResponseBody
	@RequestMapping(value = "/execute/{id}", method = RequestMethod.PUT)
	public String updateTodo(SalesPlan plan) {

		salesPlanService.updateResult(plan);

		return "1";
	}

	@RequestMapping(value = "/execute/{id}", method = RequestMethod.GET)
	public String toExec(@PathVariable("id") Long id, Map<String, Object> map) {

		SalesChance chance = salesPlanService.getDetails(id);
		map.put("chance", chance);

		return "plan/exec";
	}

	@ResponseBody
	@RequestMapping(value = "/make/{id}", method = RequestMethod.PUT)
	public String update(SalesPlan plan) {

		salesPlanService.updateTodo(plan);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/make/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {

		salesPlanService.delete(id);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/make/", method = RequestMethod.POST)
	public String save(SalesPlan plan) {

		salesPlanService.save(plan);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(plan.getDate());

		return plan.getId() + "~" + date;
	}

	@RequestMapping(value = "/make/{id}", method = RequestMethod.GET)
	public String toMake(@PathVariable("id") Long id, Map<String, Object> map) {

		SalesChance chance = salesPlanService.getDetails(id);
		map.put("chance", chance);

		return "plan/make";
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			HttpSession session, Map<String, Object> map,
			HttpServletRequest request) throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		User designee = (User) session.getAttribute("user");

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<SalesChance> page = salesPlanService.getPlanPage(pageNo, designee,
				params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);
		
		return "plan/list";
	}
}
