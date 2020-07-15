package com.edureka.actuatortests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ActuatorTestsApplicationTests {
	
	@Autowired
	private MockMvc mockMvc; // similar to restTemplate but with rich features required for testing(assertions)

	@Test
	void test_helloV2() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/hello");
		
		mockMvc.perform(requestBuilder) // similar restTemplate.getForObejct(url, class);
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Hello World")))
		;
	}
	
	@Test
	void test_hello_userV2() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/hello/Venkata");
		
		mockMvc.perform(requestBuilder) // similar restTemplate.getForObejct(url, class);
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Hello V2: Venkata")))
		;
	}
	
	@Test
	void test_hello() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
		
		mockMvc.perform(requestBuilder) // similar restTemplate.getForObejct(url, class);
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Hello World")))
		;
	}
	
	@Test
	void test_hello_user() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello/Venkata");
		
		mockMvc.perform(requestBuilder) // similar restTemplate.getForObejct(url, class);
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Hello Venkata")))
		;
	}

}
