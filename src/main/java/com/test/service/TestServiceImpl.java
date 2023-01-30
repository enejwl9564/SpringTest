package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestDao dao;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public TestDto GetTestObject(int id) {
		return dao.select(id);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TestDto InsertTestObject(TestDto dto) {
		return dao.insert(dto);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int UpdateTestObject(TestDto dto) {
		return dao.update(dto);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int DeleteTestObject(int id) {
		return dao.delete(id);
	}	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Map<String,Object>> SelectAllTestObject() {
		return dao.SelectAll();
	}	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Map<String,Object>> SelectAllTestObject(Map<String,Object> map) {
		return dao.SelectAll(map);
	}
	
}	









