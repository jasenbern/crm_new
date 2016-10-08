package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerDrainService {

	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	@Autowired
	private CustomerMapper customerMapper;

	@Transactional(readOnly = true)
	public Page<CustomerDrain> getPage(int pageNo, Map<String, Object> params)
			throws ParseException {
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = customerDrainMapper
				.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = page.getPageSize() + firstIndex;
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<CustomerDrain> content = customerDrainMapper
				.getContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public CustomerDrain get(Long id) {
		return customerDrainMapper.get(id);
	}

	@Transactional
	public void delayUpdate(CustomerDrain drain) {
		customerDrainMapper.delayUpdate(drain);
	}

	@Transactional
	public void confirmUpdate(CustomerDrain drain) {
		
		customerMapper.confirmDrain(drain.getCustomer().getId());
		
		customerDrainMapper.confirmUpdate(drain);
	}
}
