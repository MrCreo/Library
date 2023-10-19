package com.example.Library.ExceptionHandlers;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class AppError {
    private HttpStatus status;
    private String message;

    @JsonGetter(value = "status")
    public int getStatusCode() {
        return status.value();
    }

    @JsonIgnore
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
