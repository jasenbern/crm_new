package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;

public interface SalesChanceMapper {

	void dispatch(SalesChance chance);

	SalesChance getChanceForDispatch(Long id);

	void update(SalesChance chance);

	SalesChance get(@Param("id") Long id);

	void save(SalesChance chance);

	void delete(@Param("id") Long id);

	void finish(@Param("id") Long id);

	void stop(@Param("id") Long id);

	List<SalesChance> getContent(Map<String, Object> params);

	long getTotalElements(Map<String, Object> mybatisParmas);
}
