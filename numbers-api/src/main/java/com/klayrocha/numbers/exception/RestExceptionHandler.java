package com.klayrocha.numbers.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.klayrocha.numbers.resource.NumberResource;

/**
 * Class error handling
 * 
 * @author Francis Klay Rocha
 *
 */
@ControllerAdvice(assignableTypes = NumberResource.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(RestExceptionHandler.class.getName());

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOGGER.log(Level.SEVERE, "Visual Nuts ERROR : " + ex.getMessage(), ex.getMessage());
		if (ex.getCause() instanceof NumberFormatException) {
			return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "The value must be Integer number.", ex));
		}
		String error = "Internal Error, please contact the system administrator";
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	@ExceptionHandler(NegativeNumberException.class)
	private ResponseEntity<Object> negativeNumberException(NegativeNumberException dex) {
		LOGGER.log(Level.WARNING, "Visual Nuts ERROR : " + dex.getMessage(), dex.getMessage());
		return new ResponseEntity<>(new ApiError(HttpStatus.EXPECTATION_FAILED, dex.getMessage(), dex),
				HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(MaxNumberException.class)
	private ResponseEntity<Object> maxNumberException(MaxNumberException dex) {
		LOGGER.log(Level.WARNING, "Visual Nuts ERROR : " + dex.getMessage(), dex.getMessage());
		return new ResponseEntity<>(new ApiError(HttpStatus.EXPECTATION_FAILED, dex.getMessage(), dex),
				HttpStatus.EXPECTATION_FAILED);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
