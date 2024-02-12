package com.mendonca.voll_med.config.exception;

import com.mendonca.voll_med.exception.MedicoNotFoundException;
import com.mendonca.voll_med.exception.PacienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<?> handleMedicoNotFoundException(MedicoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<?> handlePacienteNotFoundException(PacienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
