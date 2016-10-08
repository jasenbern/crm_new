package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.DictMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class DictService {

	@Autowired
	private DictMapper dictMapper;

	@Transactional(readOnly = true)
	public List<Dict> getRegions() {
		return dictMapper.getRegions();
	}

	@Transactional(readOnly = true)
	public List<Dict> getLevels() {
		return dictMapper.getLevels();
	}

	@Transactional(readOnly = true)
	public List<Dict> getSatifys() {
		return dictMapper.getSatifys();
	}

	@Transactional(readOnly = true)
	public List<Dict> getCredits() {
		return dictMapper.getCredits();
	}

	@Transactional(readOnly = true)
	public List<Dict> getTypes() {
		return dictMapper.getTypes();
	}

	@Transactional(readOnly = true)
	public Page<Dict> getPage(int pageNo, Map<String, Object> params)
			throws ParseException {
		Page<Dict> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);
		long totalElements = dictMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = page.getPageSize() + firstIndex;
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<Dict> content = dictMapper.getContents(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void delete(Long id) {
		dictMapper.delete(id);
	}

	@Transactional
	public void save(Dict dict) {
		dictMapper.save(dict);
	}

	@Transactional(readOnly = true)
	public Dict get(Long id) {
		return dictMapper.get(id);
	}

	@Transactional
	public void update(Dict dict) {
		dictMapper.update(dict);
	}

}
