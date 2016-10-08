package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class StorageService {

	@Autowired
	private StorageMapper storageMapper;

	@Transactional
	public void update(Storage storage) {
		storageMapper.update(storage);
	}

	@Transactional
	public Storage get(Long id) {
		return storageMapper.get(id);
	}

	@Transactional
	public void save(Storage storage) {
		storageMapper.save(storage);
	}

	@Transactional
	public void delete(Long id) {
		storageMapper.delete(id);
	}

	@Transactional(readOnly = true)
	public Page<Storage> getPage(int pageNo, Map<String, Object> params)
			throws ParseException {

		Page<Storage> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParmas = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = storageMapper.getTotalElements(mybatisParmas);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParmas.put("firstIndex", firstIndex);
		mybatisParmas.put("endIndex", endIndex);

		List<Storage> content = storageMapper.getContent(mybatisParmas);
		page.setContent(content);

		return page;
	}
}
