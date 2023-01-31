package Mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.TestDto;
import com.test.mapper.TestMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MybatisTests {


	@Autowired
	TestMapper mapper;
	
	@Test 
	public void func1() {
		log.info("mapper : " + mapper);
		log.info("CountXML :" + mapper.countXML());
		log.info("CountAT :" + mapper.countAt());
	}
	
	@Test
	public void func2() {
//		TestDto dto = mapper.selectXML(2);
		log.info("DTOXML : " + mapper.selectXML(2));
		log.info("DTOAt : " + mapper.selectAT(3));
	}
	@Test 
	public void func3() {
		List<TestDto> list = mapper.selectXML2(1);
		for(TestDto dto : list) {
			log.info(dto);
		}
		log.info("--------------------------");
		mapper.selectAT2(2).forEach((dto)->{log.info(dto);});
	}
	
	@Test
	public void func4() {
		//mapper.insertXML(new TestDto(4,"hoho"));
		//mapper.insertAT(new TestDto(5,"test"));
		mapper.insertPARAM(6, "abc");
	}
	@Test
	public void func5() {
//		TestDto dto = new TestDto(0,"tete");
//		mapper.insertKeyBeforeXML(dto);
//		log.info("beforeKey : "+dto.getId());

		TestDto dto = new TestDto(0,"aabbcc");
		mapper.insertKeyAfterXML(dto);
		log.info("beforeKey : "+dto.getId());
	}
	@Test
	public void func6() {
		TestDto dto = new TestDto(0,"tt");
		 mapper.insertKeyBeforeAT(dto);
		 log.info("beforeKey : " + dto.getId()); //insert되기전 key 최대값
		 dto.setName("oo");
		 mapper.insertKeyAfterAT(dto);
		 log.info("afterKey : " + dto.getId());	//insert이후의 key 최대값
		 
	}
	@Test
	public void func7() {
		//mapper.updateXML(new TestDto(1,"홍길동!"));
		//mapper.deleteXML(1);
		//mapper.updateAT(new TestDto(2,"정우균"));
		mapper.deleteAT(2);
	}
	
	@Test
	public void func8() {
		Map<String,Object> map = new HashMap();	
		map.put("id", 200);
		map.put("name", "Test!!");
		mapper.insertXMLHashmap(map);		
	}
	@Test
	public void func9() {
		List<TestDto> list = new ArrayList();
		list.add(new TestDto(400,"aaa"));
		list.add(new TestDto(401,"bbb"));
		list.add(new TestDto(402,"ccc"));
		list.add(new TestDto(403,"ddd"));
		list.add(new TestDto(404,"eee"));
		
		Map<String,Object> map = new HashMap();	
		map.put("list",list);
		
		mapper.insertXMLHashmap2(map);
	}
	
	@Test
	public void func10() {
		
		List<Map<String,Object>> list = mapper.selectXMLHashMap();
		log.info("Total : " +list.size());
		list.forEach(map->{log.info(map.get("id") +","+map.get("name")   ); });
	}
}




