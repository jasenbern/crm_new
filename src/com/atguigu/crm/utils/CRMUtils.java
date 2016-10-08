package com.atguigu.crm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.atguigu.crm.entity.User;

public class CRMUtils {

	/**
	 * 用于格式化日期
	 */
	public static DateFormat dateFormat = null;

	static {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * 看起来像是生成随机字符串的。可以用UUID.randomUUID().toString().toLowerCase();代替
	 * 
	 * @return
	 */
	public static String getCustNo() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int num = random.nextInt(62);
			sb.append(str.charAt(num));
		}
		Date date = new Date();
		long time = date.getTime();
		sb.append("" + time);

		return sb.toString();
	}

	/**
	 * 把查询条件Map转成字符串形式，以&开头，以&分隔
	 * 
	 * @param params
	 * @return
	 */
	public static String parseParams2QueryString(Map<String, Object> params) {

		if (params == null || params.size() == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();

		for (Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();
			if (value == null || value.toString().trim().equals("")) {
				continue;
			}

			String key = "search_" + entry.getKey();

			builder.append("&").append(key).append("=").append(value);
		}

		return builder.toString();
	}

	/**
	 * 把查询参数的Map转换为用于Mybatis查询的Map
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> parseParmas2MybatisParams(
			Map<String, Object> params) throws ParseException {

		Map<String, Object> mybatisMap = new HashMap<>();
		if (params == null || params.size() == 0) {
			return mybatisMap;
		}

		for (Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();
			if (value == null || value.toString().trim().equals("")) {
				continue;
			}

			String key = entry.getKey();
			String[] strs = key.split("_");

			if (strs.length != 1) {
				key = strs[1];
				if ("LIKE".equalsIgnoreCase(strs[0])) {
					value = "%" + value + "%";
				}
				if ("D".equalsIgnoreCase(strs[0])) {
					// value = dateFormat.format(value);
					// Fri Sep 23 11:39:46 CST 2016 这个不能这么转
					value = dateFormat.parseObject(value.toString().trim());
				}
			}

			key = key.replace(".", "_");
			
			mybatisMap.put(key, value);
		}
		return mybatisMap;
	}

	public static String getSaltByUserName(String name) {
		
		return ByteSource.Util.bytes(name).toString();
	}

	public static String getMD5Password(User user) {
		
		String algorithmName = "MD5";
		
		String source = user.getPassword();
		
		ByteSource salt = ByteSource.Util.bytes(user.getSalt());
		
		int hashIterations = 1024;
		
		SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
		
		return simpleHash.toString();
	}

}
