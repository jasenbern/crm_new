package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.User;

public interface UserMapper {

	User getByName(@Param("name") String name);

	List<User> getAll();

	long getTotalElements(Map<String, Object> params);

	List<User> getContent(Map<String, Object> params);

	void delete(@Param("id") Long id);

	void save(User user);

	User get(@Param("id") Long id);

	void update(User user);

}
