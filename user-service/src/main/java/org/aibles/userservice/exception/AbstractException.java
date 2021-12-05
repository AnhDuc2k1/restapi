package org.aibles.userservice.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class AbstractException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

    public AbstractException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
