package com.microservices.customer.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handler(CustomerNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMsg());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handler(MethodArgumentNotValidException ex){
		var errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			var fieldName = ((FieldError)error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(errors));
	}
}
