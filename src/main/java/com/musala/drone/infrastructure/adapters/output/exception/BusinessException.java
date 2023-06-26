package com.musala.drone.infrastructure.adapters.output.exception;


import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 606401311660397983L;
	private final HttpStatus status;
	private final String errorCode;

	public BusinessException(String message) {
		super(message);
		this.errorCode = null;
		this.status = null;
	}

	public BusinessException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.errorCode = null;
	}

	public BusinessException(HttpStatus status, String message, String errorCode) {
		super(message);
		this.status = status;
		this.errorCode = errorCode;
	}

}