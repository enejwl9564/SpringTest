package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/TEST")
public class TestController {

	@GetMapping("/index")
	public void func() {
		log.info("TestController - index Page");
	}
	
	@GetMapping("/form")
	public void func2() {
		log.info("/TEST1234/form page...");
	}
	
}
