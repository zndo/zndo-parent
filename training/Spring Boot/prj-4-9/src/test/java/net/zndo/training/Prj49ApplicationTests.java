package net.zndo.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj49ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJdbcTemplat() throws Exception {

		int seed = (int) (Math.random() * 1000000);

		// TODO
	}

}
