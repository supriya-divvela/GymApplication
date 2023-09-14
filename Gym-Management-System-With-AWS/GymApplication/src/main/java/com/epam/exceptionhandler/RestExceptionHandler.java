package com.epam.exceptionhandler;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.exceptions.LoginException;
import com.epam.exceptions.TraineeException;
import com.epam.exceptions.TrainerException;
import com.epam.exceptions.UserException;

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

	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleDuplicateUserFoundException(UserException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleUserException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(TraineeException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleTraineeException(TraineeException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleTraineeException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(TrainerException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleTrainerException(TrainerException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleTrainerException");
		return new ExceptionResponse(new Date().toString(), HttpStatus.OK.name(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(LoginException.class)
	@ResponseStatus(HttpStatus.OK)
	public ExceptionResponse handleLoginException(LoginException exception, WebRequest request) {
		log.error("RestExceptionHandler:handleLoginException");
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
