package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;

	@RequestMapping("/stop/{id}")
	public String stop(@PathVariable("id") Long id) {

		salesChanceService.stop(id);

		return "redirect:/plan/list";
	}

	@RequestMapping("/finish/{id}")
	public String finish(@PathVariable("id") Long id) {

		SalesChance chance = salesChanceService.get(id);

		salesChanceService.finish(id);

		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString().toLowerCase());
		customer.setState("正常");

		customerService.save(customer);

		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);

		contactService.save(contact);

		return "redirect:/plan/list";
	}

	@RequestMapping(value = "/dispatch/", method = RequestMethod.POST)
	public String doDispatch(SalesChance chance, RedirectAttributes attributes) {

		salesChanceService.dispatch(chance);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.GET)
	public String toDispatch(@PathVariable("id") Long id,
			Map<String, Object> map) {

		SalesChance chance = salesChanceService.getChanceForDispatch(id);
		chance.setDesigneeDate(new Date());

		List<User> users = userService.getAll();

		map.put("chance", chance);
		map.put("users", users);

		return "chance/dispatch";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(SalesChance chance, RedirectAttributes attributes) {

		salesChanceService.update(chance);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		SalesChance chance = salesChanceService.get(id);
		map.put("chance", chance);

		return "chance/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(SalesChance chance, HttpSession session,
			RedirectAttributes attributes) {

		User createBy = (User) session.getAttribute("user");
		chance.setCreateBy(createBy);
		salesChanceService.save(chance);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		SalesChance chance = new SalesChance();
		chance.setCreateDate(new Date());
		map.put("chance", chance);

		return "chance/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {

		salesChanceService.delete(id);

		return "redirect:/chance/list";
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

		User createBy = (User) session.getAttribute("user");

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<SalesChance> page = salesChanceService.getPage(pageNo, createBy,
				params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "chance/list";
	}
}
