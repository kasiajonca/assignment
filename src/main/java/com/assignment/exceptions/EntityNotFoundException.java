package com.assignment.exceptions;

public class EntityNotFoundException extends RuntimeException {
    String debugMessage;

    public EntityNotFoundException(String message) {
        super(message);
        this.debugMessage = "No additional info.";
    }
}
