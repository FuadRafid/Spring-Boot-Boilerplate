package com.fuadrafid.springboot.exception.response;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationExceptionResponse {
    List<String> messages;
    LocalDateTime timestamp;

    public ValidationExceptionResponse(List<String> messages, LocalDateTime timestamp) {
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
