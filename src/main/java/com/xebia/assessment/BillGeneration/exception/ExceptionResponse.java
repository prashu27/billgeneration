package com.xebia.assessment.BillGeneration.exception;

import java.util.Date;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author prashansa.shukla
 *
 */
public class ExceptionResponse extends ResponseEntityExceptionHandler {
	private  Date timestamp;
	private String message;
	private String details;
	/**
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ExceptionResponse(Date timestamp, String message, String details) {

		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}

}
