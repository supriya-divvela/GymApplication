package com.epam.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleMethodArgumentNotValidException");
		return new ExceptionResponse(
				new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getAllErrors().stream()
						.map(error -> error.getDefaultMessage()).reduce("", (a, b) -> a + "\n" + b),
				request.getDescription(false));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleDataIntegrityViolationException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
			WebRequest request) {
		log.error("RestExceptionHandler:handleMethodArgumentTypeMismatchException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(MailSendException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleMailSendException(MailSendException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleMailSendException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleRuntimeException(RuntimeException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleRuntimeException " + exception.toString());
		return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				exception.getMessage(), request.getDescription(false));
	}
}
