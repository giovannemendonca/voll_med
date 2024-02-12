package com.mendonca.voll_med.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private LocalDateTime timestamp = LocalDateTime.now();

    private HttpStatus status;

    private int code;

    private Object message;

    private String path;


    public RestErrorMessage(HttpStatus status, int code, Object message, String path) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.path = path;
    }

}
