package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.HashMap;
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

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/dict")
@Controller
public class DictHandler {

	@Autowired
	private DictService dictService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Dict dict, RedirectAttributes attributes) {

		dictService.update(dict);

		attributes.addFlashAttribute("message", "操作成功!");

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		Dict dict = dictService.get(id);
		map.put("dict", dict);

		HashMap<String, String> editables = new HashMap<>();
		editables.put("1", "是");
		editables.put("0", "否");
		map.put("editables", editables);

		return "dict/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Dict dict, RedirectAttributes attributes) {

		dictService.save(dict);

		attributes.addFlashAttribute("message", "操作成功!");

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		Dict dict = new Dict();
		map.put("dict", dict);

		HashMap<String, String> editables = new HashMap<>();
		editables.put("1", "是");
		editables.put("0", "否");
		map.put("editables", editables);

		return "dict/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attributes) {

		dictService.delete(id);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/dict/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
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

		Page<Dict> page = dictService.getPage(pageNo, params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "dict/list";
	}
}
