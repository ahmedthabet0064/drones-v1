package com.musala.drone.infrastructure.adapters.output.exception;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessErrorResponseDTO {
	private String errorCode;
	private String message;
	private HttpStatus status;
	private LocalDateTime timestamp;

	public BusinessErrorResponseDTO(String message, String errorCode, HttpStatus status) {
		this.setErrorCode(errorCode);
		this.setMessage(message);
		this.setStatus(status);
		this.setTimestamp(LocalDateTime.now());

	}
}
