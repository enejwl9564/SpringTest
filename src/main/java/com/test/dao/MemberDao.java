package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.MemberDto;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class MemberDao {
	
	//DataSource
	@Autowired
	private DataSource ds;

	
	//INSERT
	public int Insert(MemberDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?,?)");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddr1());
			pstmt.setString(6, dto.getAddr2());
			pstmt.setString(7, "0");	//Grade
			
			result=pstmt.executeUpdate();
				
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	//SELECT
	public MemberDto Select(String email) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto dto=null;

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_member where email=?");
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				rs.next();
				dto = new MemberDto();
				dto.setEmail(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setPhone(rs.getString(3));
				dto.setZipcode(rs.getString(4));
				dto.setAddr1(rs.getString(5));
				dto.setAddr2(rs.getString(6));
				dto.setGrade(rs.getString(7));	
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return dto;
	}
	
	
	
	
	
	public int Update(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("update tbl_member set pwd=? where email=?");
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getEmail());
			
			result=pstmt.executeUpdate();
				
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}

	
}
