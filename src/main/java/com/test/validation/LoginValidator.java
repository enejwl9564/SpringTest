package com.test.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.test.dto.LoginDto;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return LoginDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginDto dto = (LoginDto)target;
		String email = dto.getEmail();
		 
		if(email==null||"".equals(email.trim())) {
			errors.rejectValue("email", "required");
		}
		else if(email==null|| email.length()<10 || email.length()>50 ) {
			errors.rejectValue("email", "invalidLength",new String[] {"","10","50"},null);
		}

	}

	
	
}
