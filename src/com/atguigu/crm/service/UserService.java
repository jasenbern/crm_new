package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional(readOnly = true)
	public User login(String name, String password) {

		User user = userMapper.getByName(name);

		if (user != null && user.getPassword().equals(password)
				&& user.getEnabled() == 1) {
			return user;
		}

		return null;
	}

	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userMapper.getAll();
	}

	@Transactional(readOnly = true)
	public Page<User> getPage(int pageNo, Map<String, Object> params)
			throws ParseException {

		Page<User> page = new Page<>();
		page.setPageNo(pageNo);

		Map<String, Object> mybatisParams = CRMUtils
				.parseParmas2MybatisParams(params);

		long totalElements = userMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);

		int firstIndex = page.getPageSize() * (page.getPageNo() - 1) + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);

		List<User> content = userMapper.getContent(mybatisParams);
		page.setContent(content);

		return page;
	}

	@Transactional
	public void save(User user) {
		
		String password = CRMUtils.getMD5Password(user);
		user.setPassword(password);
		
		userMapper.save(user);
	}

	@Transactional
	public void delete(Long id) {
		userMapper.delete(id);
	}

	@Transactional(readOnly = true)
	public User get(Long id) {
		return userMapper.get(id);
	}

	@Transactional
	public void update(User user) {
		
		String password = CRMUtils.getMD5Password(user);
		user.setPassword(password);
		
		userMapper.update(user);
	}

	@Transactional(readOnly = true)
	public User getByName(String name) {
		return userMapper.getByName(name);
	}

	@Transactional(readOnly = true)
	public boolean checkUserNameUnique(User user) {
		if (userMapper.getByName(user.getName()) == null) {
			return true;
		}
		return false;
	}

}
