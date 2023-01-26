package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dto.MemberDto;
import com.test.service.MemberService;
import com.test.validation.MemberValidator;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		
		//데이터 변환
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyymmdd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));//false :null허용x
																				// true : null허용 o
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df2,false));
		binder.registerCustomEditor(String[].class,new StringArrayPropertyEditor("-"));
		
		//유효성 체크
		binder.addValidators(new MemberValidator());
		List<Validator> vlist = binder.getValidators();
		log.info("validatorList : " + vlist);
	}
	
	
	

//	@RequestMapping(value="/join",method=RequestMethod.GET)
	@GetMapping("/join")
	public void doGet() {
		log.info("join form!!...");
	}
	
//	@RequestMapping(value="/save",method=RequestMethod.POST)
	@PostMapping("/save")
	public String doPost(@Valid @ModelAttribute MemberDto memberDto,BindingResult result,Model model ) {
		log.info("request save!!...");
		log.info("result : " + result);
		//1 파라미터 
		log.info("dto : " + memberDto);
		
		
		//2 유효성체크
		if(result.hasErrors()) {
			return "/member/join";
		}
		
		//3 서비스실행
		boolean r=service.memberJoin(memberDto);
		
		//4 뷰로이동
		model.addAttribute("msg","회원가입 성공!");
		return "redirect:/login";
		//return "joinresult";
	}

	private boolean isValid(MemberDto dto) {
		
		return false;
	}
	
}





