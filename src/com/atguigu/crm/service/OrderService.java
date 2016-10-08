package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.mapper.OrderMapper;
import com.atguigu.crm.orm.Page;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Transactional(readOnly = true)
	public Page<Order> getPage(int pageNo, Long customerId) {

		Page<Order> page = new Page<>();
		page.setPageNo(pageNo);

		long totalElements = orderMapper.getTotalElements(customerId);
		page.setTotalElements(totalElements);

		Map<String, Object> params = new HashMap<>();
		params.put("customerId", customerId);
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);

		List<Order> content = orderMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Order get(Long id) {
		return orderMapper.get(id);
	}
	@Transactional(readOnly = true)
	public List<OrderItem> getItems(Long id) {
		return orderMapper.getItems(id);
	}
}
