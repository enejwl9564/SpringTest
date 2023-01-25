package com.test.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.dto.AuthDto;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@GetMapping("/login")
	void loginform() {
		log.info("login Get...");
		
		throw new NullPointerException("login get 에서 발생!!");
		
	}
	
	@PostMapping("/login")
	String loginproc(
			String email,
			String pwd,
			boolean rememberId,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		log.info("login Post...");
		//log.info("id : " + email + " pw : " + pwd + " idchk : " + rememberId);
		
		//1 파라미터(생략)
		
		//2 유효성
		boolean flag = isValid(email,pwd);
		if(!flag) {
			model.addAttribute("msg","ID/PW가 일치하지 않습니다.");
			return "redirect:/login";
		}
		
		//3 서비스
		//1) DB id/pw 와 파라미터 id/pw 일치여부 확인
		//2) 일치하다면 - 세션객체생성 최소한의 정보를 저장
		//3) 기타(쿠키)
		HttpSession session = request.getSession();
		AuthDto adto = new AuthDto();
		adto.setEmail(email);
		adto.setGrade("1");
		session.setAttribute("authdto", adto);
		
		if(rememberId) {
			Cookie cookie = new Cookie("email",email);
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("email",email);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
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


