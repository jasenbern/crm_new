package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;

	@Transactional
	public void save(Contact contact) {
		contactMapper.save(contact);
	}

	@Transactional(readOnly = true)
	public Page<Contact> getPage(int pageNo, Long customerId) {

		Page<Contact> page = new Page<>();
		page.setPageNo(pageNo);

		long totalElements = contactMapper.getTotalElements(customerId);
		page.setTotalElements(totalElements);

		Map<String, Object> params = new HashMap<>();
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		params.put("customerId", customerId);

		List<Contact> content = contactMapper.getContent(params);
		page.setContent(content);

		return page;

	}

	@Transactional
	public void saveNew(Contact contact) {
		contactMapper.saveNew(contact);
	}

	@Transactional
	public boolean delete(Long id) {

		long num = contactMapper.checkConNum(id);
		if (num > 1) {
			contactMapper.delete(id);
			return true;
		}
		return false;
	}

	@Transactional(readOnly=true)
	public Contact get(Long id) {
		return contactMapper.get(id);
	}

	@Transactional
	public void update(Contact contact) {
		contactMapper.update(contact);
	}
}
