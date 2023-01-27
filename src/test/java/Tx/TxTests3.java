package Tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.tx.AService;
import com.test.tx.BService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TxTests3 {

	@Autowired
	AService aservice;
	
	@Autowired
	BService bservice;
	
	@Test
	public void func1() throws Exception{
		//	Tx간 독립적으로 동작
		aservice.func1();	//tx	-commit
		bservice.func1();	//tx	-commit
		
	}
	@Test
	public void func2() throws Exception{
		 				// propagation=Propagation.Required(기본값적용)
		aservice.func2();
	}
	@Test
	public void func3() throws Exception{
		aservice.func3(); 
	}
	
	
	
	

}
