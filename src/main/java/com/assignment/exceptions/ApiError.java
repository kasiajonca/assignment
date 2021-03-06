package com.assignment.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Class that will be used to wrap exception into a format readable to clients.
 */
@Data
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = "No additional info.";
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        if (ex != null) {
            this.debugMessage = ex.getMessage();
        }
    }
}
