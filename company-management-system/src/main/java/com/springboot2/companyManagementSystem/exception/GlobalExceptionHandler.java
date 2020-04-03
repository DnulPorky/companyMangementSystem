package com.springboot2.companyManagementSystem.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * Global Exception Handler is a type of workflow designed to determine the
 * project’s behavior when encountering an execution error.Only one Global
 * Exception Handler can be set per automation project.
 * 
 * @ControllerAdvice is an annotation provided by Spring allowing you to write
 *                   global code that can be applied to a wide range of
 *                   controllers, by default to classes with @Controller
 *                   annotation which extends to classes using @RestController
 * @ExceptionHandler is a Spring annotation that provides a mechanism to treat
 *                   exceptions that are thrown during execution of handlers
 *                   (Controller operations). This annotation, if used on
 *                   methods of controller classes, will serve as the entry
 *                   point for handling exceptions thrown within this controller
 *                   only.
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
