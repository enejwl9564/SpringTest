package com.test.dao;

import java.util.List;
import java.util.Map;

import com.test.dto.TestDto;

public interface TestDao {

	public TestDto select(int id);	 
	
	public TestDto insert(TestDto dto);
	
	public int update(TestDto dto);
	
	public int delete(int id);
	
	public List<Map<String,Object>> SelectAll();
	
	public List<Map<String,Object>> SelectAll(Map<String,Object> map);
	
	
}