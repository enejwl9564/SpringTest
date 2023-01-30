package com.test.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.MemberDao;
import com.test.dto.MemberDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	BCrypt bc = new BCrypt();
	
	//회원가입
	public boolean memberJoin(MemberDto dto) {
		boolean flag=false;
		//pwd를 해시화하여 저장 작업
		dto.setPwd(bc.hashpw(dto.getPwd() , bc.gensalt() ) );
		
		int result = dao.Insert(dto);
		if(result>0)
			flag=true;
		
		return flag;
	}
	
	
	
}
