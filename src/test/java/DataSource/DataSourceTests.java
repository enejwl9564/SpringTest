package DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {

	@Autowired
	DataSource ds;
	
	@Test
	public void Conn() {
		log.info("conn : " + ds);
	}
	@Test
	public void insert() throws SQLException {
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_test values(1,'홍길동')");
		int result = pstmt.executeUpdate();
		log.info("result : " + result);
	}
	@Test
	public void select() throws SQLException {
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from tbl_test");
		ResultSet rs = pstmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				log.info("id : " + rs.getInt(1));
				log.info("name : " + rs.getString(2));
			}
		}
	}
}
