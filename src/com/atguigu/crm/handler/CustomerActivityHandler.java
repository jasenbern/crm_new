package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerActivityService;
import com.atguigu.crm.service.CustomerService;

@RequestMapping("/activity")
@Controller
public class CustomerActivityHandler {

	@Autowired
	private CustomerActivityService customerActivityService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(CustomerActivity activity,RedirectAttributes attributes){
		
		customerActivityService.update(activity);
		
		attributes.addFlashAttribute("message", "操作成功！");
		
		return "redirect:/activity/list/" + activity.getCustomer().getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(Map<String, Object> map, @PathVariable("id") Long id) {

		CustomerActivity activity = customerActivityService.get(id);

		map.put("activity", activity);
		
		return "activity/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(CustomerActivity activity, RedirectAttributes attributes) {

		customerActivityService.save(activity);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/activity/list/" + activity.getCustomer().getId();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(@RequestParam("customerId") Long customerId,
			Map<String, Object> map) {

		CustomerActivity activity = new CustomerActivity();
		Customer customer = new Customer();
		customer.setId(customerId);
		activity.setCustomer(customer);

		map.put("activity", activity);

		return "activity/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam("customerId") Long customerId,
			RedirectAttributes attributes) {

		customerActivityService.delete(id);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/activity/list/" + customerId;
	}

	@RequestMapping(value = "/list/{customerId}", method = RequestMethod.GET)
	public String list(Map<String, Object> map,
			@PathVariable("customerId") Long customerId,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		Customer customer = customerService.getSimple(customerId);
		map.put("customer", customer);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerActivity> page = customerActivityService.getPage(pageNo,
				customerId);

		map.put("page", page);
		return "activity/list";
	}
}
