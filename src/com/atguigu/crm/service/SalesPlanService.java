package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.SalesPlanMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;

	@Transactional(readOnly = true)
	public Page<SalesChance> getPlanPage(int pageNo, User designee,
			Map<String, Object> params) throws ParseException {

		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParmas = CRMUtils.parseParmas2MybatisParams(params);
		mybatisParmas.put("designee", designee);
		
		
		long totalElements = salesPlanMapper.getTotalElements(mybatisParmas);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParmas.put("firstIndex", firstIndex);
		mybatisParmas.put("endIndex", endIndex);
		List<SalesChance> content = salesPlanMapper.getContent(mybatisParmas);
		page.setContent(content);

		return page;
	}

	@Transactional(readOnly = true)
	public SalesChance getDetails(Long id) {
		return salesPlanMapper.getDetails(id);
	}

	@Transactional
	public void save(SalesPlan plan) {
		salesPlanMapper.save(plan);
	}

	@Transactional
	public void delete(Long id) {
		salesPlanMapper.delete(id);
	}

	@Transactional
	public void updateTodo(SalesPlan plan) {
		salesPlanMapper.updateTodo(plan);
	}

	public void updateResult(SalesPlan plan) {
		salesPlanMapper.updateResult(plan);
	}

}
