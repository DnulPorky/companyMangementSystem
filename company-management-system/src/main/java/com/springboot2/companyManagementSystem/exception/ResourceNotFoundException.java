package com.springboot2.companyManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus is used to set HTTP response code in different scenarios, including error handling.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
