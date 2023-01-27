package com.test.mapper;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {
	
	//XML
	public int countXML();
	
	//어노테이션
	@Select("Select count(*) from tbl_a")
	public int countAt();
	
}
