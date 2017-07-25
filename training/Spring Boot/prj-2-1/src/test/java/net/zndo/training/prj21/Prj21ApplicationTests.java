package net.zndo.training.prj21;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.prj21.config.CustomProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj21ApplicationTests {

	private static Log logger = LogFactory.getLog(Prj21ApplicationTests.class);

	@Autowired
	private CustomProperties customProperties;

	@Test
	public void contextLoads() {
	}

	@Test
	public void customProperties() {
		logger.info("name : " + customProperties.getName());
		logger.info("age : " + customProperties.getAge());
		logger.info("random1 : " + customProperties.getRandom1());
		logger.info("random2 : " + customProperties.getRandom2());
		logger.info("random3 : " + customProperties.getRandom3());
		logger.info("random4 : " + customProperties.getRandom4());
		logger.info("random5 : " + customProperties.getRandom5());
	}

}
