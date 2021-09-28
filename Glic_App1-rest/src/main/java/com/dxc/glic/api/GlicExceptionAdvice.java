package com.dxc.glic.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dxc.glic.exception.EmployeeException;

@RestControllerAdvice
public class GlicExceptionAdvice {

	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<String> handleItemException(EmployeeException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
