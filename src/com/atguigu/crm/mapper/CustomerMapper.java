package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Customer;

public interface CustomerMapper {

	void save(Customer customer);

	long getTotalElements(Map<String, Object> mybatisParmas);

	List<Customer> getContent(Map<String, Object> mybatisParmas);

	Customer getDetails(@Param("id") Long id);

	void update(Customer customer);

	Customer getSimple(@Param("id") Long id);

	void delete(@Param("id") Long id);

	List<Customer> getAll();

	void confirmDrain(@Param("id") Long id);

}
