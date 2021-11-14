package com.klayrocha.country.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.klayrocha.country.CountryApiApplicationTests;
import com.klayrocha.country.service.CountryService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountryResourceTests extends CountryApiApplicationTests {
	
	
	@Autowired
	CountryService countryService;
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	String json;

	@BeforeAll
	public void setUp() throws IOException {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		json = new String(getClass().getClassLoader().getResourceAsStream("country.json").readAllBytes());
	}

	@Test
	public void testCountCountries() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/country/count")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("5", mvcResult.getResponse().getContentAsString());
		assertEquals(200, status);
	}
	
	@Test
	public void testFindCountryMostLanguagesGerman() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/country/mostGerman")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.TEXT_PLAIN))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("BE", mvcResult.getResponse().getContentAsString());
		assertEquals(200, status);
	}

	@Test
	public void testFindCountryHighestLanguagesn() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/country/highest")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.TEXT_PLAIN))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("BE", mvcResult.getResponse().getContentAsString());
		assertEquals(200, status);
	}
	
	@Test
	public void testFindMostCommonLanguage() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/country/mostLanguage")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("[\"de\",\"nl\"]", mvcResult.getResponse().getContentAsString());
		assertEquals(200, status);
	}

}
