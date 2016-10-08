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

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.service.StorageService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/storage")
@Controller
public class StorageHandler {

	@Autowired
	private StorageService storageService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Storage storage, RedirectAttributes attributes,
			@RequestParam("incrementCount") String incrementCountStr) {

		try {
			Integer stockCount = storage.getStockCount();

			stockCount += Integer.parseInt(incrementCountStr);

			storage.setStockCount(stockCount);

			storageService.update(storage);

			attributes.addFlashAttribute("message", "操作成功！");
		} catch (Exception e) {
			attributes.addFlashAttribute("message", "操作失败！");
		}

		return "redirect:/storage/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		Storage storage = storageService.get(id);
		map.put("storage", storage);
		List<Product> products = productService.getList();
		map.put("products", products);

		return "storage/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Storage storage, RedirectAttributes attributes) {

		storageService.save(storage);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/storage/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		Storage storage = new Storage();
		map.put("storage", storage);
		List<Product> products = productService.getList();
		map.put("products", products);

		return "storage/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {

		storageService.delete(id);

		return "redirect:/storage/list";
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

		Page<Storage> page = storageService.getPage(pageNo, params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "storage/list";
	}
}
