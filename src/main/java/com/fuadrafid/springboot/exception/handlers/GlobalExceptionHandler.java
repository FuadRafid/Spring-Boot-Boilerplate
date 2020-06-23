package com.fuadrafid.springboot.exception.handlers;

import com.fuadrafid.springboot.exception.ApplicationInternalException;
import com.fuadrafid.springboot.exception.response.ApplicationErrorResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Log logger = LogFactory.getLog(this.getClass());

    @ExceptionHandler(value = ApplicationInternalException.class)
    ResponseEntity<ApplicationErrorResponse> handleApplicationInternalException(final ApplicationInternalException e) {
        this.logger.error(e.getMessage(),e);
        ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(applicationErrorResponse, e.getStatusCode());
    }

}