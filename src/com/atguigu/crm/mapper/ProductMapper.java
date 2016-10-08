package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Product;

public interface ProductMapper {

	//只有id和name的list，用于form:select的显示
	List<Product> getList();

	void update(Product product);

	Product get(@Param("id") Long id);

	void save(Product product);

	void delete(@Param("id") Long id);

	List<Product> getContent(Map<String, Object> params);

	long getTotalElements(Map<String, Object> mybatisParmas);

}
