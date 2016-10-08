package com.atguigu.crm.handler;

import java.text.ParseException;
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
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/product")
@Controller
public class ProductHandler {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Product product, RedirectAttributes attributes) {

		productService.update(product);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/product/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map) {

		Product product = productService.get(id);
		map.put("product", product);

		return "product/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Product product, RedirectAttributes attributes) {

		productService.save(product);

		attributes.addFlashAttribute("message", "操作成功！");

		return "redirect:/product/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		Product product = new Product();
		map.put("product", product);

		return "product/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delele(@PathVariable("id") Long id) {

		productService.delete(id);

		return "redirect:/product/list";
	}

	@RequestMapping(value = "/list")
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

		Page<Product> page = productService.getPage(pageNo,params);

		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);
		
		return "product/list";
	}
}
