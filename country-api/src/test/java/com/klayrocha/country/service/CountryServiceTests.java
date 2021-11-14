package com.klayrocha.country.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.klayrocha.country.CountryApiApplicationTests;
import com.klayrocha.country.exception.CountryException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountryServiceTests extends CountryApiApplicationTests {

	@Autowired
	CountryService countryService;

	String json;
	String jsonInvalid;
	String jsonRepeatedLanguage;
	String jsonWithoutLanguage;

	@BeforeAll
	public void setUp() throws IOException {
		json = new String(getClass().getClassLoader().getResourceAsStream("country.json").readAllBytes());
		jsonInvalid = new String(getClass().getClassLoader().getResourceAsStream("countryInvalid.json").readAllBytes());
		jsonRepeatedLanguage = new String(getClass().getClassLoader().getResourceAsStream("countryRepeatedLanguage.json").readAllBytes());
		jsonWithoutLanguage = new String(getClass().getClassLoader().getResourceAsStream("countryWithoutLanguage.json").readAllBytes());
	}

	@Test
	public void testCountCountries() {
		assertTrue(countryService.countCountries(json) == 5);
	}
	
	@Test
	public void testFindCountryMostLanguagesGerman() {
		assertEquals("BE",countryService.findCountryMostLanguagesGerman(json));
	}

	@Test
	public void testFindCountryHighestLanguagesn() {
		assertEquals("BE",countryService.findCountryHighestLanguagesn(json));
	}
	
	@Test
	public void testFindMostCommonLanguage() {
		assertEquals("[de, nl]",countryService.findMostCommonLanguage(json).toString());
	}
	
	
	@Test
	public void testJsonInvalid() {
		try {
			countryService.countCountries(jsonInvalid);
		} catch (CountryException e) {
			assertEquals("Invalid JSON format", e.getMessage());
		}
	}
	
	
	@Test
	public void testJsonRepeatedLanguage() {
		try {
			countryService.countCountries(jsonRepeatedLanguage);
		} catch (CountryException e) {
			assertEquals("BE contains repeated language", e.getMessage());
		}
	}
	
	@Test
	public void testJsonWithoutLanguage() {
		try {
			countryService.countCountries(jsonWithoutLanguage);
		} catch (CountryException e) {
			assertEquals("In the list there is a country without a language", e.getMessage());
		}
	}

}
