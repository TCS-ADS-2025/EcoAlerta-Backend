package com.ecoalerta.app.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessages = new ArrayList<String>();

        exception.getBindingResult().getFieldErrors().forEach(ex -> {
            var messageError = ex.getDefaultMessage();
            errorMessages.add(messageError);
        });

        return ResponseEntity.status(status).body(new RestErrorMessage(HttpStatus.valueOf(status.value()), errorMessages));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> handleEntityNotFound(EntityNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        var message = exception.getMessage();

        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }
}