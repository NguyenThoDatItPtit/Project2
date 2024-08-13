package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.Model.ErrorResponseDTO;

import customexeption.FieldRequiredExeption;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handerArithmeticExeption(
			ArithmeticException ex, WebRequest request){
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		List<String> details = new ArrayList<>();
		details.add("so nguyen lam sao chia het cho 0 ");
		errorResponseDTO.setDetail(details);
		errorResponseDTO.setError(ex.getMessage());
		return new ResponseEntity<Object>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldRequiredExeption.class)
	public ResponseEntity<Object> handerFieldRequiredExeption(
			FieldRequiredExeption ex, WebRequest request){
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		List<String> details = new ArrayList<>();
		details.add("check lai name hoac number vi dang bi null do");
		errorResponseDTO.setDetail(details);
		errorResponseDTO.setError(ex.getMessage());
		return new ResponseEntity<Object>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
	}
}
