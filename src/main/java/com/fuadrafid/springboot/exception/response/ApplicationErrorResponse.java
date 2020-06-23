package com.fuadrafid.springboot.exception.response;

import java.time.LocalDateTime;

public class ApplicationErrorResponse {
    String message;
    LocalDateTime timestamp;

    public ApplicationErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
