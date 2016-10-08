package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {

	void save(@Param("customer") Customer customer);

	long getTotalElements(Map<String, Object> mybatisParams);

	List<CustomerDrain> getContent(Map<String, Object> mybatisParams);

	CustomerDrain get(@Param("id") Long id);

	void delayUpdate(CustomerDrain drain);

	void confirmUpdate(CustomerDrain drain);

	void delete(@Param("customerId") Long id);

}
