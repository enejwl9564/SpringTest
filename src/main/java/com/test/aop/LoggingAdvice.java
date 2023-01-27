package com.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Aspect
@Log4j
public class LoggingAdvice {
	
	//execution(반환타입 패키지.클래스.메소드(인자))
	@Around("execution(* com.test.aop.MyMath.*(..))")
	public Object logging1(ProceedingJoinPoint pjp) throws Throwable{
		
		long start = System.currentTimeMillis();
		log.info("[AOP] start : " +pjp.getSignature().getName());
		Object result = pjp.proceed(); //로직 실행 
		log.info("[AOP] result : " + result);
		long end =  System.currentTimeMillis();
		log.info("[AOP] time : " + (end-start)+"ms");
		log.info("[AOP] end ");
		return result;	
	}
}




