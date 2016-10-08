package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerActivity;

public interface CustomerActivityMapper {

	long getTotalElements(@Param("customerId") Long customerId);

	List<CustomerActivity> getContent(Map<String, Object> params);

	void delete(@Param("id") Long id);

	void save(CustomerActivity activity);

	CustomerActivity get(@Param("id") Long id);

	void update(CustomerActivity activity);

}
