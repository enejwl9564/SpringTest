package com.test.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.test.dto.MemberDto;

public class MemberValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return MemberDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberDto dto = (MemberDto)target;
		String email = dto.getEmail();
		String pwd = dto.getPwd();
		
		Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		Matcher passMatcher1 = passPattern1.matcher(pwd);
		 
		if(email==null||"".equals(email.trim())) {
			errors.rejectValue("email", "required");
		}
		else if(email==null|| email.length()<10 || email.length()>50 ) {
			errors.rejectValue("email", "invalidLength",new String[] {"","10","50"},null);
		}
		else if(pwd==null||"".equals(pwd.trim())) {
			errors.rejectValue("pwd", "required");
		}
		else if(pwd==null|| !passMatcher1.find())
		{
			errors.rejectValue("pwd", "invalidPolicy");
		}
		 
	}

	
	
}
