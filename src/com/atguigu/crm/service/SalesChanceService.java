package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;

	@Transactional
	public void dispatch(SalesChance chance) {
		salesChanceMapper.dispatch(chance);
	}

	@Transactional(readOnly = true)
	public SalesChance getChanceForDispatch(Long id) {
		return salesChanceMapper.getChanceForDispatch(id);
	}

	@Transactional
	public void update(SalesChance chance) {
		salesChanceMapper.update(chance);
	}

	@Transactional(readOnly = true)
	public SalesChance get(Long id) {
		return salesChanceMapper.get(id);
	}

	@Transactional
	public void save(SalesChance chance) {
		salesChanceMapper.save(chance);
	}

	@Transactional
	public void delete(Long id) {
		salesChanceMapper.delete(id);
	}

	@Transactional
	public void finish(Long id) {
		salesChanceMapper.finish(id);
	}

	@Transactional
	public void stop(Long id) {
		salesChanceMapper.stop(id);
	}

	@Transactional(readOnly = true)
	public Page<SalesChance> getPage(int pageNo, User createBy,
			Map<String, Object> params) throws ParseException {
		
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParmas = CRMUtils.parseParmas2MybatisParams(params);
		
		mybatisParmas.put("createBy", createBy);
		
		long totalElements = salesChanceMapper.getTotalElements(mybatisParmas);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParmas.put("firstIndex", firstIndex);
		mybatisParmas.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.getContent(mybatisParmas);
		
		page.setContent(content);
		
		return page;
	}
	


}
