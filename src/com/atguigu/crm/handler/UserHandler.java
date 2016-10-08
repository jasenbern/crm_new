package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/user")
@Controller
public class UserHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceBundleMessageSource messageSouce;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(User user, RedirectAttributes attributes) {

		String salt = CRMUtils.getSaltByUserName(user.getName());
		user.setSalt(salt);

		userService.update(user);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(Map<String, Object> map, @PathVariable("id") Long id) {

		User user = userService.get(id);
		map.put("user", user);

		List<Role> roles = roleService.getList();
		map.put("roles", roles);

		Map<Integer, String> status = new HashMap<>();
		status.put(1, "有效");
		status.put(0, "无效");
		map.put("status", status);

		return "user/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(User user, RedirectAttributes attributes) {

		if (userService.checkUserNameUnique(user)) {

			String salt = CRMUtils.getSaltByUserName(user.getName());
			user.setSalt(salt);

			userService.save(user);

			attributes.addFlashAttribute("message", "操作成功！");
		} else {
			attributes.addFlashAttribute("message", "用户名已被占用！");
		}
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		map.put("user", new User());

		List<Role> roles = roleService.getList();
		map.put("roles", roles);

		Map<Integer, String> status = new HashMap<>();
		status.put(1, "有效");
		status.put(0, "无效");
		map.put("status", status);

		return "user/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {

		userService.delete(id);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/list")
	public String list(
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

		Page<User> page = userService.getPage(pageNo, params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "user/list";
	}

	@RequestMapping("/login")
	public String login(@RequestParam("name") String name,
			@RequestParam("password") String password, HttpSession session,
			RedirectAttributes redirectAttributes, Locale locale) {

		Subject currentUser = SecurityUtils.getSubject();
		
		//if (!currentUser.isAuthenticated()) {
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);

			Object user = currentUser.getPrincipals().getPrimaryPrincipal();
			
			session.setAttribute("user", user);
			
		} catch (AuthenticationException ae) {
			String code = "error.crm.user.login";
			Object[] args = null;
			String message = messageSouce.getMessage(code, args, locale);

			redirectAttributes.addFlashAttribute("message", message);

			return "redirect:/index";
		}
		//}
		
		
		return "redirect:/success";

	}
}
