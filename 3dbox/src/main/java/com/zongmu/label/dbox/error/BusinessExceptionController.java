package com.zongmu.label.dbox.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zongmu.label.dbox.locale.LocaleService;

@ControllerAdvice
public class BusinessExceptionController {
	private static Logger logger = LoggerFactory.getLogger(BusinessExceptionController.class);

	@Autowired
	private LocaleService localeService;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> UnexpectedError(Exception exception) {
		logger.error("Unkown error.", exception);
		return this.BusinessError(new BusinessException(ErrorCodes.General_Error));
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> HttpMediaTypeNotSupportedError(HttpMediaTypeNotSupportedException exception) {
		logger.error("HttpMediaTypeNotSupportedException", exception);
		return this.BusinessError(new BusinessException(ErrorCodes.BAD_REQUEST_NOT_ACCEPT_CONTENT_TYPE));
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> BadCredentialsError(BadCredentialsException exception) {
		logger.error("BadCredentialsException", exception);
		return this.BusinessError(new BusinessException(ErrorCodes.Login_Failed_Due_To_Password_Invalid));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDeniedException(AccessDeniedException exception) {
		logger.error("AccessDeniedException", exception);
		return this.BusinessError(new BusinessException(ErrorCodes.Login_Failed_Due_To_Password_Invalid));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> BusinessError(BusinessException businessException) {
		businessException.setMessage(this.localeService.getErrorMessage(businessException.getCode()));
		logger.error("BusinessException", businessException);
		return new ResponseEntity<Object>(new BusinessExceptionResponse(businessException), HttpStatus.BAD_REQUEST);
	}
}
