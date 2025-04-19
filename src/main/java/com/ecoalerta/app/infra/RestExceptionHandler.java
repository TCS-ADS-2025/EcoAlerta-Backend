package com.ecoalerta.app.infra;

import com.ecoalerta.app.infra.exceptions.CategoriaSemComentarioException;
import com.ecoalerta.app.infra.exceptions.CronogramaSemDiaSemanaException;
import com.ecoalerta.app.infra.exceptions.UsuarioSemComentarioException;
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
    private ResponseEntity<RestErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessages = new ArrayList<String>();

        e.getBindingResult().getFieldErrors().forEach(ex -> {
            var messageError = ex.getDefaultMessage();
            errorMessages.add(messageError);
        });

        return ResponseEntity.status(status).body(new RestErrorMessage(HttpStatus.valueOf(status.value()), errorMessages));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> handleEntityNotFound(EntityNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        var message = e.getMessage();

        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }

    @ExceptionHandler(CategoriaSemComentarioException.class)
    private ResponseEntity<RestErrorMessage> handleCategoriaSemComentario(CategoriaSemComentarioException e) {
        var status = HttpStatus.NOT_FOUND;
        var message = e.getMessage();

        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }

    @ExceptionHandler(CronogramaSemDiaSemanaException.class)
    private ResponseEntity<RestErrorMessage> handleCronogramaSemDiaSemana(CronogramaSemDiaSemanaException e) {
        var status = HttpStatus.NOT_FOUND;
        var message = e.getMessage();

        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }

    @ExceptionHandler(UsuarioSemComentarioException.class)
    private ResponseEntity<RestErrorMessage> handleUarioSemComentario(UsuarioSemComentarioException e) {
        var status = HttpStatus.NOT_FOUND;
        var message = e.getMessage();

        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }
}