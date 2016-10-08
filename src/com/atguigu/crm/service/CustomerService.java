package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerDrainMapper customerDrainMapper;

	@Transactional
	public void save(Customer customer) {
		customerMapper.save(customer);
	}

	@Transactional(readOnly = true)
	public Page<Customer> getPage(int pageNo, Map<String, Object> params)
			throws ParseException {
		Page<Customer> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParmas = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = customerMapper.getTotalElements(mybatisParmas);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParmas.put("firstIndex", firstIndex);
		mybatisParmas.put("endIndex", endIndex);

		List<Customer> content = customerMapper.getContent(mybatisParmas);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Customer getDetails(Long id) {
		return customerMapper.getDetails(id);
	}

	@Transactional
	public void update(Customer customer) {
		
		if("流失预警".equals(customer.getState())){
			customerDrainMapper.save(customer);
		}
		
		customerMapper.update(customer);
	}

	@Transactional(readOnly = true)
	public Customer getSimple(Long customerId) {
		return customerMapper.getSimple(customerId);
	}

	@Transactional
	public void delete(Long id) {
		
		customerDrainMapper.delete(id);
		
		customerMapper.delete(id);
	}

	@Transactional(readOnly = true)
	public List<Customer> getAll() {
		return customerMapper.getAll();
	}
}
