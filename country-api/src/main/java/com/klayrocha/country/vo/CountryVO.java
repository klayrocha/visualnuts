package com.klayrocha.country.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CountryVO implements Serializable, Comparable<CountryVO> {

	private static final long serialVersionUID = 3714311807749071964L;

	private String country;
	private List<String> languages;
	
	
	@Override
	public int compareTo(CountryVO o) {
		return ((Integer) o.getLanguages().size()).compareTo(this.languages.size());
	}

}
