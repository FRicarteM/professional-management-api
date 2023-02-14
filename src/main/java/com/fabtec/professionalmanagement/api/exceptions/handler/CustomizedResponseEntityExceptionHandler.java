package com.fabtec.professionalmanagement.api.exceptions.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fabtec.professionalmanagement.api.exceptions.BadRequestException;
import com.fabtec.professionalmanagement.api.exceptions.ExceptionResponse;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	private DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.title("INTERNAL SERVER ERROR")
				.timestamp(dtf.format(LocalDateTime.now()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.title("NOT FOUND")
				.timestamp(dtf.format(LocalDateTime.now()))
				.status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.title("BAD REQUEST")
				.timestamp(dtf.format(LocalDateTime.now()))
				.status(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	 
	
}