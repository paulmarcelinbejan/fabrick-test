package io.github.paulmarcelinbejan.fabrick.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickExceptionResponse;

import feign.FeignException;

@RestControllerAdvice
public class FabrickExceptionController {

	@ResponseBody
	@ExceptionHandler(FeignException.class)
	public FabrickExceptionResponse handleFeignException(FeignException exception, HttpServletResponse response) {
		HttpStatus httpStatus = HttpStatus.resolve(exception.status());
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setStatus(httpStatus.value());

		return new FabrickExceptionResponse(httpStatus, exception, "FeignException");
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public FabrickExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return new FabrickExceptionResponse(
				HttpStatus.BAD_REQUEST, 
				exception, 
				exception.getClass().getSimpleName(),
				getPrettyMethodArgumentNotValidExceptionMessage(exception));
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ValidationException.class, ConstraintViolationException.class, IllegalArgumentException.class })
	public FabrickExceptionResponse handleBadRequest(RuntimeException exception) {
		return new FabrickExceptionResponse(HttpStatus.BAD_REQUEST, exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { NoSuchElementException.class })
	public FabrickExceptionResponse handleNoSuchElementException(NoSuchElementException exception) {
		return new FabrickExceptionResponse(HttpStatus.NOT_FOUND, exception);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public FabrickExceptionResponse handleAllUncaughtException(Exception exception) {
		return new FabrickExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
	}
	
	private String getPrettyMethodArgumentNotValidExceptionMessage(MethodArgumentNotValidException exception) {
		return exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList().toString();
	}

}
