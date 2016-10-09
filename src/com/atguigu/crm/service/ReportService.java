package com.atguigu.crm.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class ReportService {

	@Autowired
	private ReportMapper reportMapper;

	@Transactional(readOnly = true)
	public Page<Map<String, Object>> getContributePage(int pageNo,
			Map<String, Object> params) throws ParseException {

		Page<Map<String, Object>> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = reportMapper
				.getContributeTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<Map<String, Object>> content = reportMapper
				.getContributeContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Page<Map<String, Object>> getConsistPage(int pageNo, String type) {

		Page<Map<String, Object>> page = new Page<>();
		page.setPageNo(pageNo);

		long totalElements = reportMapper.getConsistTotalElements(type);
		page.setTotalElements(totalElements);

		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);

		List<Map<String, Object>> content = reportMapper
				.getConsistContent(params);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Page<Map<String, Object>> getServicePage(int pageNo,
			Map<String, Object> params) throws ParseException {
		Page<Map<String, Object>> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = reportMapper
				.getServiceTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<Map<String, Object>> content = reportMapper
				.getServiceContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Page<Map<String, Object>> getDrainPage(int pageNo,
			Map<String, Object> params) throws ParseException {
		Page<Map<String, Object>> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = reportMapper.getDrainTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<Map<String, Object>> content = reportMapper
				.getDrainContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public Map<String, Integer> getConsistNumMap(String type) {

		List<Map<String, Object>> list = reportMapper.getConsistMap(type);

		Map<String, Integer> consistNumMap = new HashMap<>();
		for (Map<String, Object> map : list) {
			consistNumMap.put((String) map.get("type"), ((BigDecimal) map.get("num")).intValue());
		}

		return consistNumMap;
	}
}
