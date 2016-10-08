package com.atguigu.crm.test;

import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.utils.CRMUtils;

public class FormalTest {

	@Test
	public void test1() {
		System.out.println(new Date().toString());
		String format = CRMUtils.dateFormat.format(new Date().toString());
		System.out.println(format);
	}

	@Test
	public void test2() {

		String s = "na.me";
		// s.split("\\.");
		String split = s.replace(".", "_");
		System.out.println(split);
	}

	@Test
	public void test3() {
		CustomerService service = new CustomerService();
		service.setCreateDate(new Date());
		service.setDealDate(new Date());
		service.setAllotDate(new Date());
		System.out.println(service.getCreateDate());
		System.out.println(service.getDealDate());
		System.out.println(service.getAllotDate());
	}

	@Test
	public void test4() {
		User user = new User();
		user.setName("bcde");
		user.setPassword("123456");

		String salt = CRMUtils.getSaltByUserName(user.getName());
		user.setSalt(salt);

		String password = CRMUtils.getMD5Password(user);
		user.setPassword(password);

		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getSalt());
	}

	@Test
	public void test5() {
		System.out.println(new SimpleByteSource("bcde"));
		System.out.println(new SimpleByteSource("bcde".getBytes()));
		System.out.println(new SimpleByteSource("bcde".toCharArray()));
	}

	@Test
	public void test6() {
//		String algorithmName = "MD2";
		String algorithmName = "MD5";
//		String algorithmName = "SHA-1";
//		String algorithmName = "SHA-256";
//		String algorithmName = "SHA-384";
//		String algorithmName = "SHA-512";

		String source = "123456";

		ByteSource salt = ByteSource.Util.bytes("salt");

		int hashIterations = 1024;

		SimpleHash simpleHash2 = new SimpleHash(algorithmName, source);
		
		SimpleHash simpleHash3 = new SimpleHash(algorithmName, source, salt);
		
		SimpleHash simpleHash4 = new SimpleHash(algorithmName, source, salt,
				hashIterations);

		System.out.println(simpleHash2);
		System.out.println(simpleHash3);
		System.out.println(simpleHash4);
		
	}

}
