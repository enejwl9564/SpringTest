package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.dto.TestDto;

public interface TestService {
	
	public TestDto GetTestObject(int id);

	public TestDto InsertTestObject(TestDto dto);
	
	public int UpdateTestObject(TestDto dto);
	
	public int DeleteTestObject(int id);
	
	public List<Map<String,Object>> SelectAllTestObject();
	
	public List<Map<String,Object>> SelectAllTestObject(Map<String,Object> map);
}