package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;

@RequestMapping("/role")
@Controller
public class RoleHandler {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Role role, RedirectAttributes attributes) {

		roleService.updateAthorities(role);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/role/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String toDispatch(@PathVariable("id") Long id,
			Map<String, Object> map) {

		Role role = roleService.get(id);

		List<Authority> parentAuthorities = roleService
				.getAllParentAuthorities();

		map.put("role", role);
		map.put("parentAuthorities", parentAuthorities);

		return "role/dispatch";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {

		roleService.delete(id);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/role/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Role role, RedirectAttributes attributes) {

		roleService.save(role);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/role/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input() {
		return "role/input";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr) {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Role> page = roleService.getPage(pageNo);

		map.put("page", page);

		return "role/list";
	}
}
