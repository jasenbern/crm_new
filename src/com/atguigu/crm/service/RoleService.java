package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.AuthorityMapper;
import com.atguigu.crm.mapper.RoleMapper;
import com.atguigu.crm.orm.Page;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private AuthorityMapper authorityMapper;

	@Transactional(readOnly = true)
	public Page<Role> getPage(int pageNo) {

		Page<Role> page = new Page<>();
		page.setPageNo(pageNo);

		long totalElements = roleMapper.getTotalElements();
		page.setTotalElements(totalElements);

		Map<String, Object> params = new HashMap<>();
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);

		List<Role> content = roleMapper.getContent(params);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void save(Role role) {
		roleMapper.save(role);
	}

	@Transactional
	public void delete(Long id) {
		roleMapper.delete(id);
	}

	@Transactional(readOnly = true)
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Transactional(readOnly = true)
	public List<Authority> getAllParentAuthorities() {
		return authorityMapper.getAllParentAuthorities();
	}

	@Transactional
	public void updateAthorities(Role role) {
		// 批量删除中间表
		// 批量加入中间表
		roleMapper.clearAuthorities(role);
		roleMapper.addAuthorities(role);
	}

	@Transactional(readOnly = true)
	public List<Role> getList() {
		return roleMapper.getList();
	}
}
