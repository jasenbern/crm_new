package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;


public interface ContactMapper {

	void save(Contact contact);

	long getTotalElements(@Param("customerId") Long customerId);

	List<Contact> getContent(Map<String, Object> params);

	void saveNew(Contact contact);

	long checkConNum(@Param("id") Long id);

	void delete(@Param("id") Long id);

	Contact get(@Param("id") Long id);

	void update(Contact contact);

}
