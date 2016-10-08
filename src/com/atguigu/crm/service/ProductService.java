package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CRMUtils;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Transactional(readOnly=true)
	public List<Product> getList(){
		return productMapper.getList();
	}
	
	@Transactional
	public void update(Product product){
		productMapper.update(product);
	}
	
	@Transactional(readOnly=true)
	public Product get(Long id){
		return productMapper.get(id);
	}
	
	@Transactional
	public void save(Product product){
		productMapper.save(product);
	}
	
	@Transactional
	public void delete(Long id){
		productMapper.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Product> getPage(int pageNo, Map<String, Object> params) throws ParseException {

		Page<Product> page = new Page<>();
		page.setPageNo(pageNo);
		
		Map<String, Object> mybatisParmas = CRMUtils.parseParmas2MybatisParams(params);
		
		long totalElements = productMapper.getTotalElements(mybatisParmas);
		page.setTotalElements(totalElements);

		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		mybatisParmas.put("firstIndex", firstIndex);
		mybatisParmas.put("endIndex", endIndex);
		
		List<Product> content = productMapper.getContent(mybatisParmas);
		page.setContent(content);

		return page;
	}
}
