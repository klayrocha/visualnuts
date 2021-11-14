package com.klayrocha.numbers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.klayrocha.numbers.exception.MaxNumberException;
import com.klayrocha.numbers.exception.NegativeNumberException;
import com.klayrocha.numbers.util.Constants;

@Service
public class NumberService {

	private static final Logger LOGGER = Logger.getLogger(NumberService.class.getName());

	@Value("${max-number}")
	private int maxNumber;

	@Value("${first-number}")
	private int firstNumber;

	@Value("${secound-number}")
	private int secoundNumber;

	/**
	 * Mthod responsible for print sequence of integer numbers
	 * @param lastNumber
	 * @return
	 */
	public List<String> print(int lastNumber) {
		validate(lastNumber);

		List<String> result = new ArrayList<>();

		for (int i = 1; i <= lastNumber; i++) {
			if (isDivisibleBy(firstNumber, i) && isDivisibleBy(secoundNumber, i)) {
				result.add(Constants.VISUAL_NUTS);
			} else {
				if (isDivisibleBy(firstNumber, i)) {
					result.add(Constants.VISUAL);
				} else if (isDivisibleBy(secoundNumber, i)) {
					result.add(Constants.NUTS);
				} else {
					result.add(""+i);
				}
			}

		}
		return result;
	}

	/**
	 * Method responsible for verify if the number is divisible
	 * @param divider
	 * @param value
	 * @return
	 */
	private boolean isDivisibleBy(int divider, int value) {
		return value % divider == 0;
	}

	/**
	 * Method responsible for validate the number
	 * 
	 * @param value
	 * @return boolean
	 */
	public void validate(int lastNumber) {
		if (lastNumber < 0) {
			throw new NegativeNumberException("The number cannot be negative");
		}
		if (lastNumber > maxNumber) {
			throw new MaxNumberException("The value cannot be greater than " + maxNumber);
		}
		LOGGER.info("Validated Number");
	}

}
