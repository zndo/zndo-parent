package net.zndo.training.prj1;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.zndo.training.prj1.web.IndexController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj1ApplicationTests {

	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}

	@Test
	public void mockIndexRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/index")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("你好，这是首页内容。")));

	}

}
