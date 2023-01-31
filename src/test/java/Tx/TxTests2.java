//package Tx;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.test.service.TestService;
//
//import lombok.extern.log4j.Log4j;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@Log4j
//public class TxTests2 {
//
//	@Autowired
//	TestService service;
//	
//	@Test
//	public void func1() throws Exception{
//		service.func1();		//Tx 기본 값 적용
//	}
//	@Test
//	public void func2() throws Exception{
//		service.func2();		//모든 Exception에 대한 Tx 적용
//	}
//	@Test
//	public void func3() throws Exception{
//		service.func3();		//RuntimeException에 대한 Tx 적용
//	}
//	
//	
//	
//	
//
//}
