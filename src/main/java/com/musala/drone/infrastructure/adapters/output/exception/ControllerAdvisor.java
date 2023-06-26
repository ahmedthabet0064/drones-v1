package com.musala.drone.infrastructure.adapters.output.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

	public static final String TIMESTAMP = "timestamp";
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";
	public static final String ERRORCODE = "errorCode";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessErrorResponseDTO> handleBusinessException(BusinessException ex,
			WebRequest webRequest) {
		BusinessErrorResponseDTO responseDTO = new BusinessErrorResponseDTO(ex.getMessage(), ex.getErrorCode(),
				ex.getStatus());
		return new ResponseEntity<BusinessErrorResponseDTO>(responseDTO, ex.getStatus());
	}
}
