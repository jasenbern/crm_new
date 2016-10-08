package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.CustomerServiceMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class CustomerServiceService {

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Transactional
	public void save(CustomerService service) {
		customerServiceMapper.save(service);
	}

	@Transactional(readOnly = true)
	public Page<CustomerService> getAllotPage(int pageNo, User createdby,
			Map<String, Object> params) throws ParseException {

		Page<CustomerService> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		mybatisParams.put("createdby", createdby);

		long totalElements = customerServiceMapper
				.getAllotTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<CustomerService> content = customerServiceMapper
				.getAllotContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void delete(Long id) {
		customerServiceMapper.delete(id);
	}

	@Transactional
	public void allotUpdate(CustomerService service) {
		customerServiceMapper.allotUpdate(service);
	}

	@Transactional(readOnly = true)
	public Page<CustomerService> getDealPage(int pageNo, User allotTo,
			Map<String, Object> params) throws ParseException {

		Page<CustomerService> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		mybatisParams.put("allotTo", allotTo);

		long totalElements = customerServiceMapper
				.getDealTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<CustomerService> content = customerServiceMapper
				.getDealContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public CustomerService get(Long id) {
		CustomerService customerService = customerServiceMapper.get(id);
		return customerService;
	}

	@Transactional
	public void dealUpdate(CustomerService service) {
		customerServiceMapper.dealUpdate(service);
	}

	@Transactional(readOnly = true)
	public Page<CustomerService> getFeedbackPage(int pageNo,
			Map<String, Object> params) throws ParseException {

		Page<CustomerService> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = customerServiceMapper
				.getFeedbackTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<CustomerService> content = customerServiceMapper
				.getFeedbackContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void feedbackUpdate(CustomerService service) {
		customerServiceMapper.feedbackUpdate(service);
	}

	@Transactional(readOnly = true)
	public Page<CustomerService> getArchivePage(int pageNo,
			Map<String, Object> params) throws ParseException {
		Page<CustomerService> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = customerServiceMapper
				.getArchiveTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<CustomerService> content = customerServiceMapper
				.getArchiveContent(mybatisParams);
		page.setContent(content);

		return page;
	}
}
