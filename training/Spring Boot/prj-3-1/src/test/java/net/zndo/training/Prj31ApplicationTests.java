package net.zndo.training;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.zndo.training.web.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj31ApplicationTests {

	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserController() throws Exception {

		// 测试-用户列表
		mvc.perform(MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		mvc.perform(MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		mvc.perform(MockMvcRequestBuilders.post("/user/create").param("name", "测试人员1").param("age", "28")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		mvc.perform(MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		mvc.perform(MockMvcRequestBuilders.put("/user/update/1").param("name", "测试人员2").param("age", "18")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		mvc.perform(MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		mvc.perform(MockMvcRequestBuilders.delete("/user/remove/1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

	}
}
