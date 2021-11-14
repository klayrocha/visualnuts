package com.klayrocha.country.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.klayrocha.country.resource.CountryResource;

/**
 * Class error handling
 * 
 * @author Francis Klay Rocha
 *
 */
@ControllerAdvice(assignableTypes = CountryResource.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(RestExceptionHandler.class.getName());

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOGGER.log(Level.SEVERE, "Visual Nuts ERROR : " + ex.getMessage(), ex.getMessage());
		String error = "Internal Error, please contact the system administrator";
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	@ExceptionHandler(CountryException.class)
	private ResponseEntity<Object> countryException(CountryException dex) {
		LOGGER.log(Level.WARNING, "Visual Nuts ERROR : " + dex.getMessage(), dex.getMessage());
		return new ResponseEntity<>(new ApiError(HttpStatus.EXPECTATION_FAILED, dex.getMessage(), dex),
				HttpStatus.EXPECTATION_FAILED);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
