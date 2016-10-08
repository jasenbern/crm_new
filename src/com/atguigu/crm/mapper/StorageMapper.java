package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Storage;

public interface StorageMapper {

	void update(Storage storage);

	Storage get(@Param("id") Long id);

	void save(Storage storage);

	void delete(@Param("id") Long id);

	List<Storage> getContent(Map<String, Object> params);

	long getTotalElements(Map<String, Object> mybatisParmas);
}
