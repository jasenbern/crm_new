package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;

@RequestMapping("/contact")
@Controller
public class ContactHandler {

	@Autowired
	private ContactService contactService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Contact contact, RedirectAttributes attributes) {

		contactService.update(contact);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/contact/list/" + contact.getCustomer().getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		Contact contact = contactService.get(id);
		map.put("contact", contact);

		return "contact/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Contact contact, RedirectAttributes attributes) {

		contactService.saveNew(contact);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/contact/list/" + contact.getCustomer().getId();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map,
			@RequestParam("customerId") Long customerId) {

		Customer customer = new Customer();
		customer.setId(customerId);
		Contact contact = new Contact();
		contact.setCustomer(customer);
		map.put("contact", contact);

		return "contact/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam("customerId") Long customerId,
			RedirectAttributes attributes) {

		boolean success = contactService.delete(id);

		if (success) {
			attributes.addFlashAttribute("message", "操作成功！");
		} else {
			attributes.addFlashAttribute("message", "仅有一个联系人，不能删除！");
		}

		return "redirect:/contact/list/" + customerId;
	}

	@RequestMapping(value = "/list/{customerId}", method = RequestMethod.GET)
	public String list(@PathVariable("customerId") Long customerId,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {

		Customer customer = customerService.getSimple(customerId);
		map.put("customer", customer);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Contact> page = contactService.getPage(pageNo, customerId);
		map.put("page", page);

		return "contact/list";
	}
}
