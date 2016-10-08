package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
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

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/customer")
@Controller
public class CustomerHandler {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private DictService dictService;

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {

		customerService.delete(id);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Customer customer, RedirectAttributes attributes) {

		customerService.update(customer);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		Customer customer = customerService.getDetails(id);

		map.put("customer", customer);

		List<Dict> regions = dictService.getRegions();
		List<Dict> levels = dictService.getLevels();
		List<Dict> satifys = dictService.getSatifys();
		List<Dict> credits = dictService.getCredits();

		map.put("regions", regions);
		map.put("levels", levels);
		map.put("satifys", satifys);
		map.put("credits", credits);

		return "customer/input";
	}

	@RequestMapping("/list")
	public String list(Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			HttpServletRequest request) throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<Customer> page = customerService.getPage(pageNo, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		List<Dict> regions = dictService.getRegions();
		List<Dict> levels = dictService.getLevels();

		map.put("regions", regions);
		map.put("levels", levels);

		return "customer/list";
	}
}
