package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dto.Criteria;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/list")
	public String doGet(Integer pageno,HttpServletRequest req,HttpServletResponse resp) {
			log.info("board list...pageno : " + pageno);
			System.out.println(req.getSession().getServletContext().getRealPath("/"));
			//1 파라미터
			
			//2 유효성
			
			//3 서비스
			Criteria criteria=null;
			if(pageno==null) 	//최초 /board/list 접근
			{
				criteria = new Criteria(); //pageno=1 , amount=10
			}
			else
			{
				//페이지이동 요청 했을때
				criteria = new Criteria(pageno,10);
			}
			boolean result=service.GetBoardList(criteria,req,resp);
			
			//4 뷰(생략)
			return "/board/list";		
	}
	
	
	@GetMapping("read")
	public void dogetRead() {
		log.info("board read....");
	}
	@GetMapping("post")
	public void dogetPost() {
		log.info("board post....");
	}
	
	
	
	
}
