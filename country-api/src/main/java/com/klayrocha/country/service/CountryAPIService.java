package com.klayrocha.country.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klayrocha.country.exception.CountryException;
import com.klayrocha.country.vo.CountryVO;

public class CountryAPIService {

	
	private static final Logger LOGGER = Logger.getLogger(CountryService.class.getName());
	
	protected List<CountryVO> validate(String json) {
		List<CountryVO> countries = convertToListCountryVO(json);
		validateCountryWithoutLanguage(countries);
		validateRepeatedLanguage(countries);
		return countries;
	}
	
	/**
	 * Method responsible for convert json to List CountryVO
	 * @param json
	 * @return
	 */
	protected List<CountryVO> convertToListCountryVO(String json){
		ObjectMapper mapper = new ObjectMapper();
		List<CountryVO> countries = null;
		try {
			if(json != null && !json.contains("\"country\"")) {
				json = json.replaceAll("country","\"country\"");
				json = json.replaceAll("languages","\"languages\"");
			}
			countries = Arrays.asList(mapper.readValue(json, CountryVO[].class));
		} catch (JsonMappingException e) {
			LOGGER.severe("Invalid JSON format"+e.toString());
			throw new CountryException("Invalid JSON format");
		} catch (JsonProcessingException e) {
			LOGGER.severe("Invalid JSON format"+e.toString());
			throw new CountryException("Invalid JSON format");
		}
		return countries;
	}
	
	/**
	 * Method responsible for validate the number
	 * 
	 * @param value
	 * @return boolean
	 */
	protected void validateCountryWithoutLanguage(List<CountryVO> countries) {
		if(countries != null && countries.size() > 0) {
			countries.stream().forEach(c -> {
				if(c.getLanguages().size() == 0) {
					LOGGER.severe("In the list there is a country without a language");
					throw new CountryException("In the list there is a country without a language");
				}
			});
		}
	}
	
	
	/**
	 * Method responsible for validate the repeated Languages
	 * 
	 * @param value
	 * @return boolean
	 */
	protected void validateRepeatedLanguage(List<CountryVO> countries) {
		if(countries != null && countries.size() > 0) {
			countries.stream().forEach(c -> {
				Set<String> listSet = new HashSet<>();
				c.getLanguages().stream().forEach(l -> {
					listSet.add(l);
				});
				if(c.getLanguages().size() > listSet.size()) {
					LOGGER.severe(c.getCountry()+" contains repeated language");
					throw new CountryException(c.getCountry()+" contains repeated language");
				}
			});
		}
	}
}
