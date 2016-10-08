package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Dict;

public interface DictMapper {

	List<Dict> getRegions();

	List<Dict> getLevels();

	List<Dict> getSatifys();

	List<Dict> getCredits();

	List<Dict> getTypes();

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Dict> getContents(Map<String, Object> mybatisParams);

	void delete(@Param("id") Long id);

	void save(Dict dict);

	Dict get(@Param("id")Long id);

	void update(Dict dict);

}
