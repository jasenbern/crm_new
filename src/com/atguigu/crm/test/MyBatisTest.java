package com.atguigu.crm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.mapper.CustomerServiceMapper;

public class MyBatisTest {

	private ApplicationContext ctx = null;
//	private SqlSessionFactory sessionFactory = null;
//	private OrderMapper orderMapper = null;
//	private CustomerMapper customerMapper = null;
	private CustomerServiceMapper customerServiceMapper = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		sessionFactory = ctx.getBean(SqlSessionFactory.class);
//		orderMapper = ctx.getBean(OrderMapper.class);
//		customerMapper = ctx.getBean(CustomerMapper.class);
		customerServiceMapper = ctx.getBean(CustomerServiceMapper.class);
	}
	
	@Test
	public void test01(){
		CustomerService customerService = customerServiceMapper.get(2126L);
		System.out.println(customerService.getAllotDate());
	}

}
