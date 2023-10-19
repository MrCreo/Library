package com.example.Library.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<AppError> handle(ResponseStatusException e) {
        AppError error = new AppError();
        error.setStatus(e.getStatus());
        error.setMessage(e.getReason());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> validationException(MethodArgumentNotValidException e) {
        AppError validationError = new AppError();
        FieldError error = e.getBindingResult()
                .getFieldError();
        validationError.setStatus(HttpStatus.BAD_REQUEST);
        if (error != null)
            validationError.setMessage(error.getField() + " " + error.getDefaultMessage());
        return new ResponseEntity<>(validationError, validationError.getStatus());
    }
}
