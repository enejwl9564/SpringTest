//package Mybatis;
//
//import java.sql.Connection;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import lombok.extern.log4j.Log4j;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@Log4j
//public class SqlSessionTests {
//
//	@Autowired
//	SqlSessionFactory ssf;
//	
//	@Autowired
//	SqlSessionTests session;
//	
//	@Test 
//	public void func1() {
//		log.info("SSF : "+ssf);
//		SqlSessionTests dbs = ssf.openSession();
//		Connection conn = dbs.getConnection();
//		log.info("CONN : " + conn);
//	}
//	@Test 
//	public void func2() {
//		log.info("session : " + session);
//		Connection conn = session.getConnection();
//		log.info("CONN : " + conn);	
//	}
//}
//
//
//
//
