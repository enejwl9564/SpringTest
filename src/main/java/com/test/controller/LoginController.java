package com.test.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.dto.LoginDto;
import com.test.service.AuthService;
import com.test.validation.LoginValidator;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {

	@Autowired
	AuthService service;
	
	@InitBinder
	public void IDPWInit(WebDataBinder binder) {
		//유효성 체크
		binder.addValidators(new LoginValidator());
		List<Validator> vlist = binder.getValidators();
		log.info("validatorList : " + vlist);
	}	
	
	
	@GetMapping("/login")
	void loginform() {
		log.info("login Get...");
		
//		throw new NullPointerException("login get 에서 발생!!");
		
	}
	
	@PostMapping("/login")
	String loginproc(
			@Valid @ModelAttribute LoginDto loginDto,
			BindingResult result,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		log.info("login Post...");
		//log.info("id : " + email + " pw : " + pwd + " idchk : " + rememberId);
		
		//1 파라미터(생략)
		
		//2 유효성
		if(result.hasErrors()) {
			return "/login";
		}
		
		//3 서비스
		//1) DB id/pw 와 파라미터 id/pw 일치여부 확인
		//2) 일치하다면 - 세션객체생성 최소한의 정보를 저장
		//3) 기타(쿠키)
		boolean flag = service.LoginCheck(loginDto, request);
		if(flag)
		{
			if(loginDto.isRememberId()) {
				Cookie cookie = new Cookie("email",loginDto.getEmail());
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("email",loginDto.getEmail());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		
		
		//4 뷰
		return "redirect:/";
		
	}
	private boolean isValid(String email, String pwd) {
		return email.equals("abcd@naver.com")&&pwd.equals("1234");
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		//1 세션종료
		session.invalidate();
		
		//2 메세지
		model.addAttribute("msg","로그아웃 성공!");
		
		//3 login이동
		return "redirect:/login";
	}
	
	
	
}


