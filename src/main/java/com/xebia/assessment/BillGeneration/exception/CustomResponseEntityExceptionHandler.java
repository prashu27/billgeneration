package com.xebia.assessment.BillGeneration.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author prashansa.shukla
 *
 *CustomResponseEntityExceptionHandler to handle the exceptions globally
 */
@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final  ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req){
		ExceptionResponse response=	new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return  new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(InvalidBillException.class)
	public final  ResponseEntity<Object> handleBadRequestExceptions(Exception ex, WebRequest req){
		ExceptionResponse response=	new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return  new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}

}
