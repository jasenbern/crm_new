package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	long getTotalElements();

	List<Role> getContent(Map<String, Object> params);

	void save(Role role);

	void delete(@Param("id") Long id);

	Role get(@Param("id") Long id);

	void clearAuthorities(Role role);

	void addAuthorities(Role role);

	List<Role> getList();

}
