package com.klayrocha.numbers.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.klayrocha.numbers.NumbersApiApplicationTests;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberResourceTests extends NumbersApiApplicationTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Value("${max-number}")
	private int maxNumber;

	@BeforeAll
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testEndPointUp() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/number/"+maxNumber).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testNegativeNumber() throws Exception {
		int number = -2;
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/number/"+number).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("{\"status\":\"EXPECTATION_FAILED\",\"message\":\"The number cannot be negative\"}", mvcResult.getResponse().getContentAsString());
		assertEquals(417, status);
	}
	
	@Test
	public void testMaxNumber() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/number/"+maxNumber+1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("{\"status\":\"EXPECTATION_FAILED\",\"message\":\"The value cannot be greater than "+maxNumber+"\"}", mvcResult.getResponse().getContentAsString());
		assertEquals(417, status);
	}
	
	@Test
	public void testSendText() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/number/text").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("{\"status\":\"INTERNAL_SERVER_ERROR\",\"message\":\"The value must be Integer number.\"}", mvcResult.getResponse().getContentAsString());
		assertEquals(500, status);
	}

}
