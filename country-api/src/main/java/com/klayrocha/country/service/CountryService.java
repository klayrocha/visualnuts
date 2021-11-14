package com.klayrocha.country.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.klayrocha.country.vo.CountryVO;

@Service
public class CountryService extends CountryAPIService {

	/**
	 * Method responsible for count the number of countries in the world
	 * 
	 * @param json
	 * @return the number of countries in the world
	 */
	public Integer countCountries(String json) {
		List<CountryVO> countries = validate(json);
		return countries.size();
	}

	/**
	 * Method responsible for finds the country with the most official languages,
	 * where they officially speak German (de). - that counts all the official
	 * languages spoken in the listed countries.
	 * 
	 * @param json
	 * @return
	 */
	public String findCountryMostLanguagesGerman(String json) {
		List<CountryVO> countries = validate(json);
		Collections.sort(countries);
		if (countries != null && countries.size() > 0) {
			for (CountryVO country : countries) {
				for (String language : country.getLanguages()) {
					if (language.equalsIgnoreCase("de")) {
						return country.getCountry();
					}
				}
			}
		}
		return "";

	}

	/**
	 * Method responsible to find the country with the highest number of
	 * official languages.
	 * 
	 * @param json
	 * @return
	 */
	public String findCountryHighestLanguagesn(String json) {
		List<CountryVO> countries = validate(json);
		Collections.sort(countries);
		return countries.get(0).getCountry();
	}
	
	/**
	 * Method responsible to find the most common official language(s), of all countries.
	 * official languages.
	 * 
	 * @param json
	 * @return
	 */
	public List<String> findMostCommonLanguage(String json) {
		List<CountryVO> countries = validate(json);
		
		Map<String, Integer> qtdLanguages = new HashMap<>();
		countries.stream().forEach(c -> {
			c.getLanguages().stream().forEach(l -> {
				if(qtdLanguages.containsKey(l)) {
					Integer value = qtdLanguages.get(l);
					qtdLanguages.put(l,value+1);
				} else {
					qtdLanguages.put(l,1);
				}
			});
		});
		
		List<String> result = new ArrayList<String>();
		
		List<Entry<String, Integer>> collect = qtdLanguages
			.entrySet().stream()
		    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		    .collect(Collectors.toList());
		
		int qtd = 0;
		for (Entry<String, Integer> entry : collect) {
			if(qtd == 0) {
				qtd = entry.getValue();
				result.add(entry.getKey());
			} else {
				if(entry.getValue() == qtd) {
					qtd = entry.getValue();
					result.add(entry.getKey());
				} else {
					break;
				}
			}
		}
		return result;
	}

}
