package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;

public interface OrderMapper {

	long getTotalElements(@Param("customerId") Long customerId);

	List<Order> getContent(Map<String, Object> params);

	Order get(@Param("id") Long id);

	List<OrderItem> getItems(@Param("id") Long id);

}
