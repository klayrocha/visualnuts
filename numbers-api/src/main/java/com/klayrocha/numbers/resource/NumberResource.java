package com.klayrocha.numbers.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klayrocha.numbers.service.NumberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Numbers")
@RestController
@RequestMapping("/number")
@RequiredArgsConstructor
public class NumberResource {

	private final NumberService numberService;

	@ApiOperation(value = "Print sequence of numbers", tags = { "Number Services" })
	@GetMapping(value = "/{lastNumber}", produces = { "application/json" })
	public ResponseEntity<?> print(@ApiParam(value = "lastNumber" , example = "100", required = true)  @PathVariable("lastNumber") int lastNumber) {
		return new ResponseEntity<>(numberService.print(lastNumber), HttpStatus.OK);
	}

}
