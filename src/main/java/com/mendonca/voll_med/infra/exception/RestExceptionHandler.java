package com.mendonca.voll_med.infra.exception;

import com.mendonca.voll_med.exception.MedicoNotFoundException;
import com.mendonca.voll_med.exception.PacienteNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(MedicoNotFoundException.class)
    private ResponseEntity<RestErrorMessage> handleMedicoNotFoundException(MedicoNotFoundException ex,
                                                                           HttpServletRequest request) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    private ResponseEntity<RestErrorMessage> handlePacienteNotFoundException(PacienteNotFoundException ex,
                                                                             HttpServletRequest request) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                   HttpServletRequest request) {

        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream().forEach(
                fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        RestErrorMessage message = new RestErrorMessage(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                errors,
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErrorMessage> handleException(Exception ex,
                                                             HttpServletRequest request) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

}
