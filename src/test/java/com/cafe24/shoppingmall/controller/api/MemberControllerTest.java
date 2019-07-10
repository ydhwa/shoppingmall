package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.SwaggerConfig;
import com.cafe24.shoppingmall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SwaggerConfig.class})
@WebAppConfiguration
public class MemberControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void testJoin() throws Exception {
		MemberVo vo = new MemberVo("user1", "1234", "유저1", "1996-09-18", "031-111-1111", "010-1111-1111", "test1@test1.com", true, false);
		
		ResultActions resultActions = mockMvc
			.perform(post("/api/member/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
			resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.name", is(vo.getName())))
				.andExpect(jsonPath("$.data.contents", is(vo.getName())));
	}
}
