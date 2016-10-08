package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.mapper.CustomerActivityMapper;
import com.atguigu.crm.orm.Page;

@Service
public class CustomerActivityService {

	@Autowired
	private CustomerActivityMapper customerActivityMapper;

	@Transactional(readOnly = true)
	public Page<CustomerActivity> getPage(int pageNo, Long customerId) {

		Page<CustomerActivity> page = new Page<>();
		page.setPageNo(pageNo);

		long totalElements = customerActivityMapper
				.getTotalElements(customerId);
		page.setTotalElements(totalElements);

		Map<String, Object> params = new HashMap<>();
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		params.put("customerId", customerId);

		List<CustomerActivity> content = customerActivityMapper
				.getContent(params);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void delete(Long id) {
		customerActivityMapper.delete(id);
	}

	@Transactional
	public void save(CustomerActivity activity) {
		customerActivityMapper.save(activity);
	}

	@Transactional(readOnly=true)
	public CustomerActivity get(Long id) {
		return customerActivityMapper.get(id);
	}

	@Transactional
	public void update(CustomerActivity activity) {
		customerActivityMapper.update(activity);
	}
}
