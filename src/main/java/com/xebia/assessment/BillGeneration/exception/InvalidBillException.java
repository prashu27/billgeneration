package com.xebia.assessment.BillGeneration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidBillException(String message) {
		super(message);
	}

}
