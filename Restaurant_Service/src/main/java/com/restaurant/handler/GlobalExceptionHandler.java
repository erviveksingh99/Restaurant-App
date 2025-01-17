package com.restaurant.handler;

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

import com.restaurant.customexception.IdNotFoundException;
import com.restaurant.customexception.ResourceNotFoundException;
import com.restaurant.error.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(WebRequest req) {
		ErrorDetails details = new ErrorDetails(
				HttpStatus.NOT_FOUND.value(),  // Use Spring's HttpStatus
				"Id is invalid please check your id currectly",
				req.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
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
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(WebRequest req)
	{
		ErrorDetails details = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", req.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
