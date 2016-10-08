package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	long getTotalElements(Map<String, Object> mybatisParmas);

	List<SalesChance> getContent(Map<String, Object> params);

	SalesChance getDetails(@Param("id") Long id);

	void save(SalesPlan plan);

	void delete(@Param("id") Long id);

	void updateTodo(SalesPlan plan);

	void updateResult(SalesPlan plan);
}
