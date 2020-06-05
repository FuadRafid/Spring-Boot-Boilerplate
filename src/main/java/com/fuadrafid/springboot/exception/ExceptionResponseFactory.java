package com.fuadrafid.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ExceptionResponseFactory {
    private ResponseEntity<Map<String, Object>> getCustomErrorResponse(final HttpStatus status, final String message, final Object data) {
        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", message);

        if (data != null) {
            body.put("data", data);
        }
        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<Map<String, Object>> handleException(ServiceException e) {
        switch (e.getCode()) {
            case WRONG_NUMBER_FORMAT:
                return this.getCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong number format, inputs must be integers", null);
            case DIVIDE_BY_ZERO:
                return this.getCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot divide by 0", null);
            case ERROR_CREATING_EMPLOYEE:
                return this.getCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create employee", null);
            case ERROR_FETCHING_EMPLOYEE_DATA:
                return this.getCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get employee data", null);
            default:
                return this.getCustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", null);
        }
    }
}
