package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderHandler {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/details/{id}")
	public String details(Map<String, Object> map,@PathVariable("id")Long id) {

		Order order = orderService.get(id);
		map.put("order", order);
		
		List<OrderItem> items = orderService.getItems(id);
		map.put("items", items);
		
		return "order/details";
	}

	@RequestMapping(value = "/list/{customerId}")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			@PathVariable("customerId") Long customerId, Map<String, Object> map) {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Order> page = orderService.getPage(pageNo, customerId);
		map.put("page", page);

		return "order/list";
	}
}
