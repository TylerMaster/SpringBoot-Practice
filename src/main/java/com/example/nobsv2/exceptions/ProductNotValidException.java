package com.example.nobsv2.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotValidException extends RuntimeException {

	public ProductNotValidException(String message) {
		
		super(message);
	}
}
