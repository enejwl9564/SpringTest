package com.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
	String email;
	String pwd;
	boolean rememberId;
}
