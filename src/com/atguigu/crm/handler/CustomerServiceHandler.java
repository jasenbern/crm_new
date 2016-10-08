package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerServiceService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/service")
@Controller
public class CustomerServiceHandler {

	@Autowired
	private CustomerServiceService customerServiceService;
	@Autowired
	private DictService dictService;
	@Autowired
	private com.atguigu.crm.service.CustomerService customerService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/archive/{id}", method = RequestMethod.GET)
	public String archiveInput(@PathVariable("id") Long id,
			Map<String, Object> map) {

		CustomerService service = customerServiceService.get(id);
		map.put("service", service);

		return "service/archive/input";
	}
	
	@RequestMapping(value = "/archive/list", method = RequestMethod.GET)
	public String archiveList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<CustomerService> page = customerServiceService.getArchivePage(
				pageNo, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "service/archive/list";
	}
	
	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.PUT)
	public String feedbackUpdate(CustomerService service,
			RedirectAttributes attributes) {

		customerServiceService.feedbackUpdate(service);
		
		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/service/feedback/list";
	}

	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
	public String feedbackInput(@PathVariable("id") Long id,
			Map<String, Object> map) {

		CustomerService service = customerServiceService.get(id);
		map.put("service", service);

		List<Dict> satisfys = dictService.getSatifys();
		map.put("satisfys", satisfys);

		return "service/feedback/input";
	}

	@RequestMapping(value = "/feedback/list", method = RequestMethod.GET)
	public String feedbackList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<CustomerService> page = customerServiceService.getFeedbackPage(
				pageNo, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "service/feedback/list";
	}

	@RequestMapping(value = "/deal/{id}", method = RequestMethod.PUT)
	public String dealUpdate(CustomerService service,
			RedirectAttributes attributes) {

		customerServiceService.dealUpdate(service);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/service/deal/list";
	}

	@RequestMapping(value = "/deal/{id}", method = RequestMethod.GET)
	public String dealInput(@PathVariable("id") Long id, Map<String, Object> map) {

		CustomerService service = customerServiceService.get(id);
		service.setDealDate(new Date());

		map.put("service", service);

		return "service/deal/input";
	}

	@RequestMapping(value = "/deal/list", method = RequestMethod.GET)
	public String dealList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		User user = (User) session.getAttribute("user");

		Page<CustomerService> page = customerServiceService.getDealPage(pageNo,
				user, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "service/deal/list";
	}

	@ResponseBody
	@RequestMapping(value = "/allot/{id}", method = RequestMethod.PUT)
	public String allotUpdate(@PathVariable("id") Long id,
			@RequestParam("allotId") Long allotId) {

		CustomerService service = new CustomerService();
		service.setId(id);
		
		service.setAllotDate(new Date());
		
		User user = new User();
		user.setId(allotId);
		service.setAllotTo(user);
		
		customerServiceService.allotUpdate(service);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {

		customerServiceService.delete(id);

		return "1";
	}

	@RequestMapping(value = "/allot/list", method = RequestMethod.GET)
	public String allotList(HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		User user = (User) session.getAttribute("user");

		Page<CustomerService> page = customerServiceService.getAllotPage(
				pageNo, user, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		List<User> users = userService.getAll();
		map.put("users", users);

		return "service/allot/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(CustomerService service, HttpSession session,
			RedirectAttributes attributes) {

		service.setCreatedby((User) session.getAttribute("user"));

		customerServiceService.save(service);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/service/allot/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		CustomerService service = new CustomerService();
		service.setCreateDate(new Date());
		map.put("service", service);

		List<Dict> types = dictService.getTypes();
		map.put("types", types);

		List<Customer> customers = customerService.getAll();
		map.put("customers", customers);

		return "service/input";
	}
}
