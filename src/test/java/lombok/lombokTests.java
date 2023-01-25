package lombok;

import org.junit.Test;

import com.test.dto.BoardDto;
import com.test.dto.PersonDto;

public class lombokTests {

	@Test
	public void lombokTests1() {
		PersonDto dto = new PersonDto();		
		dto.setName("홍길동");
		dto.setAge("55");
		dto.setAddr("대구");
		System.out.println(dto);
	}

	@Test
	public void lombokTests2() {
		BoardDto dto = new BoardDto();
		System.out.println(dto);
		BoardDto dto2 = new BoardDto("1","1","1","1","1","1","1","1","1");
		System.out.println(dto2);
		BoardDto dto3 = new BoardDto().builder()
				.no("1")
				.title("제목")
				.content("내용")
				.filename("파일명")
				.email("example@naver.com")
				.build();
		System.out.println(dto3);
	}
	
}
