//package com.test.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import lombok.extern.log4j.Log4j;
//
//@WebFilter("/*")
//@Log4j
//public class forwardloginfilter implements Filter{
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException {
//		
//		
//		//Request요청 전 처리
//		log.info("ForwardLoginFilter Start!.....");
//		
//		HttpServletRequest request = (HttpServletRequest)req;
//		HttpServletResponse response = (HttpServletResponse)resp;
//		
//		//로그인이 된 상태인지 유무 확인
//		HttpSession session = request.getSession(true);	 //true(기본) : 세션객체 없으면 새로 생성
//														 //false : 세션객체 없으면 null
//		
//		if(session.getAttribute("authdto")==null) //no login
//		{
//
//			if(request.getRequestURI().contains("/resources")) {
//				chain.doFilter(req, resp);
//				return ;
//			}
//			if(request.getRequestURI().contains("/member/join")) {
//				chain.doFilter(req, resp);
//				return ;
//			}
//			if(request.getRequestURI().contains("/member/save")) {
//				chain.doFilter(req, resp);
//				return ;
//			}
//		
//			req.getRequestDispatcher("/login").forward(request, response);
//			return ;
//		}
//		else //로그인 한상태
//		{
//			
//		
//		}
//		
//		//로그인한 상태
//		chain.doFilter(req, resp);
//		
//		
// 
//		
//		
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}
