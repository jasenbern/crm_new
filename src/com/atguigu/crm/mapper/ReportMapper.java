package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReportMapper {

	long getConsistTotalElements(@Param("type") String type);

	List<Map<String, Object>> getConsistContent(Map<String, Object> params);

	long getContributeTotalElements(Map<String, Object> params);

	List<Map<String, Object>> getContributeContent(Map<String, Object> params);

	// TODO: 还没有写~
	long getServiceTotalElements(Map<String, Object> params);

	// TODO: 还没有写~
	List<Map<String, Object>> getServiceContent(Map<String, Object> params);

	// TODO: 还没有写~
	long getDrainTotalElements(Map<String, Object> params);

	// TODO: 还没有写~
	List<Map<String, Object>> getDrainContent(Map<String, Object> params);
}
