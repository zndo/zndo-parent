package net.zndo.training;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.zndo.training.web.controller.HomeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj81ApplicationTests {

	private MockMvc mvc;

	@Before
	public void setup() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	@Test
	public void contextLoads() {
	}

	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello world")));
	}

}
