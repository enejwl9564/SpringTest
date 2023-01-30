package com.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class TestDaoImpl implements TestDao {
	
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.test.mapper.TestMapper.";
	
	//SELECTone
	@Override
	public TestDto select(int id) {
		return session.selectOne(namespace+"selectXML",id);
	}
	
	//Insert(SelectKey)
	@Override
	public TestDto insert(TestDto dto) {
		session.insert(namespace+"insertKeyAfterXML", dto);
		return dto;
	}
	
	//Update
	@Override
	public int update(TestDto dto) {
		return  session.update(namespace+"updateXML",dto);
	}
	
	//Delete
	@Override
	public int delete(int id) {
		return session.delete(namespace+"deleteXML",id);
	}
	
	//SelectAll
	@Override
	public List<Map<String,Object>> SelectAll(){
		return session.selectList(namespace+"selectXMLHashMap");
	}

	//SelectAll+map
	@Override
	public List<Map<String,Object>> SelectAll(Map<String,Object> map){
		return session.selectList(namespace+"selectXMLHashMapIf",map);
	}
	
}




