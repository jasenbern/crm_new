package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerDrainService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/drain")
@Controller
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService customerDrainService;

	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.PUT)
	public String confirmUpdate(CustomerDrain drain,
			RedirectAttributes attributes) {

		drain.setDrainDate(new Date());

		customerDrainService.confirmUpdate(drain);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/drain/list";
	}

	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public String confirmInput(@PathVariable("id") Long id,
			Map<String, Object> map) {

		CustomerDrain drain = customerDrainService.get(id);

		map.put("drain", drain);

		return "drain/confirm";
	}

	@RequestMapping(value = "/delay/{id}", method = RequestMethod.PUT)
	public String delayUpdate(@RequestParam("delayAdd") String delayAdd,
			CustomerDrain drain, RedirectAttributes attributes) {

		drain.setDelay(drain.getDelay() + "`" + delayAdd);

		customerDrainService.delayUpdate(drain);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/drain/list";
	}

	@RequestMapping(value = "/delay/{id}", method = RequestMethod.GET)
	public String delayInput(@PathVariable("id") Long id,
			Map<String, Object> map) {

		CustomerDrain drain = customerDrainService.get(id);

		map.put("drain", drain);

		return "drain/delay";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Map<String, Object> map, HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) String pageNoStr)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "drain/list";
	}
}
