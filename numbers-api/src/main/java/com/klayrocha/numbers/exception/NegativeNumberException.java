package com.klayrocha.numbers.exception;

/**
 * Class for exception handling for numbers
 * 
 * @author Francis Klay Rocha
 *
 */
public class NegativeNumberException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegativeNumberException(String msg) {
		super(msg);
	}

}
