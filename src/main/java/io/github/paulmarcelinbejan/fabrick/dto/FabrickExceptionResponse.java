package io.github.paulmarcelinbejan.fabrick.dto;

import java.time.Instant;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class FabrickExceptionResponse {

	private final String uniqueIdentifier;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private final Instant timestampUTC;

	private final String status;

	private final String error;

	private final String exceptionType;

	private final String message;
	
	public FabrickExceptionResponse(HttpStatus httpStatus, Exception exception) {
		this(httpStatus, exception, exception.getClass().getSimpleName());
	}

	public FabrickExceptionResponse(HttpStatus httpStatus, Exception exception, String exceptionType) {
		this(httpStatus, exception, exceptionType, exception.getMessage());
	}

	public FabrickExceptionResponse(HttpStatus httpStatus, Exception exception, String exceptionType, String message) {
		this(httpStatus, exception, exceptionType, message, UUID.randomUUID().toString());
	}

	public FabrickExceptionResponse(HttpStatus httpStatus, Exception exception, String exceptionType, String message, String uniqueIdentifier) {
		this(uniqueIdentifier, 
				String.valueOf(httpStatus.value()), 
				httpStatus.getReasonPhrase(), 
				exceptionType, 
				message);
		log.error("Exception Stack Trace of " + uniqueIdentifier, exception);
	}

	@JsonCreator
	public FabrickExceptionResponse(
			String uniqueIdentifier, 
			String status, 
			String error,
			String exceptionType, 
			String message) {
		
		this.uniqueIdentifier = uniqueIdentifier;
		timestampUTC = Instant.now();
		this.status = status;
		this.error = error;
		this.exceptionType = exceptionType;
		this.message = message;

	}

}

