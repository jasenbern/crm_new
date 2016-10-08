package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerService;

public interface CustomerServiceMapper {

	void save(CustomerService service);

	long getAllotTotalElements(Map<String, Object> mybatisParams);

	List<CustomerService> getAllotContent(Map<String, Object> mybatisParams);

	void delete(@Param("id") Long id);

	void allotUpdate(CustomerService service);

	long getDealTotalElements(Map<String, Object> mybatisParams);

	List<CustomerService> getDealContent(Map<String, Object> mybatisParams);

	CustomerService get(@Param("id") Long id);

	void dealUpdate(CustomerService service);

	long getFeedbackTotalElements(Map<String, Object> mybatisParams);

	List<CustomerService> getFeedbackContent(Map<String, Object> mybatisParams);

	void feedbackUpdate(CustomerService service);

	long getArchiveTotalElements(Map<String, Object> mybatisParams);

	List<CustomerService> getArchiveContent(Map<String, Object> mybatisParams);

}
