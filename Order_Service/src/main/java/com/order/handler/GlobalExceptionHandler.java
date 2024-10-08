package com.order.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.order.customexception.IdNotFoundException;
import com.order.customexception.ResourceNotFoundException;
import com.order.error.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(NoResourceFoundException ex, WebRequest req) {
		ErrorDetails details = new ErrorDetails(
				org.springframework.http.HttpStatus.NOT_FOUND.value(),  // Use Spring's HttpStatus
				ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(details, org.springframework.http.HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleIdNotFoundException(WebRequest req)
	{
		ErrorDetails details = 
				new ErrorDetails(HttpStatus.BAD_REQUEST.value(), 
						"Id is invalid", 
						req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
	
	// Handle validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		// Collect all validation errors for each field
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		// Return BAD_REQUEST status
		return new ResponseEntity<>(errors, org.springframework.http.HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(WebRequest req)
	{
		ErrorDetails details =
				new ErrorDetails
				(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value(), "An Unexpected internal server error.", 
						req.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
