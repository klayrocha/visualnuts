package com.klayrocha.numbers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.klayrocha.numbers.NumbersApiApplicationTests;
import com.klayrocha.numbers.exception.MaxNumberException;
import com.klayrocha.numbers.exception.NegativeNumberException;

public class NumberServiceTests extends NumbersApiApplicationTests {

	@Autowired
	NumberService numberService;

	@Value("${max-number}")
	private int maxNumber;

	@Test
	public void testFindFullList() {
		assertNotNull(numberService.print(maxNumber));
	}

	@Test
	public void testSizeFindFullList() {
		assertEquals(maxNumber, numberService.print(maxNumber).size());
	}

	@Test
	public void testNegativeNumber() {
		try {
			numberService.print(-2);
		} catch (NegativeNumberException e) {
			assertEquals("The number cannot be negative", e.getMessage());
		}
	}

	@Test
	public void testMaxNumber() {
		try {
			numberService.print(maxNumber+1);
		} catch (MaxNumberException e) {
			assertEquals("The value cannot be greater than " + maxNumber, e.getMessage());
		}
	}

}
