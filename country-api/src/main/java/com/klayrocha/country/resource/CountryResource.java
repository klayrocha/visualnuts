package com.klayrocha.country.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klayrocha.country.service.CountryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Countries")
@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryResource {
	
	private final CountryService countryService;
	
	
	@ApiOperation(value = "Number of countries in the world", tags = { "Country Services" })
	@PostMapping(value = "/count", produces = { "application/json" }, consumes = { "application/json" })
	//public ResponseEntity<?> countCountries(@RequestBody List<CountryVO> countries) { TODO IT COULD BE LIKE THIS, IF THE EXAMPLE JSON WERE IN THE CORRECT FORMAT
	public ResponseEntity<?> countCountries(@ApiParam(value = "json" , example = "json", required = true) @RequestBody String json) {
		return new ResponseEntity<>(countryService.countCountries(json), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Find the country with the highest number official languages, where they officially speak German (de).", tags = { "Country Services" })
	@PostMapping(value = "/mostGerman", produces = { "text/plain" }, consumes = { "application/json" })
	public ResponseEntity<?> findCountryMostLanguagesGerman(@ApiParam(value = "json" , example = "json", required = true) @RequestBody String json) {
		return new ResponseEntity<>(countryService.findCountryMostLanguagesGerman(json), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find the country with the highest number official languages.", tags = { "Country Services" })
	@PostMapping(value = "/highest", produces = { "text/plain" }, consumes = { "application/json" })
	public ResponseEntity<?> findCountryHighestLanguagesn(@ApiParam(value = "json" , example = "json", required = true) @RequestBody String json) {
		return new ResponseEntity<>(countryService.findCountryHighestLanguagesn(json), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find the most common official language(s), of all countries.", tags = { "Country Services" })
	@PostMapping(value = "/mostLanguage", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> findMostCommonLanguage(@ApiParam(value = "json" , example = "json", required = true) @RequestBody String json) {
		return new ResponseEntity<>(countryService.findMostCommonLanguage(json), HttpStatus.OK);
	}
	
}
