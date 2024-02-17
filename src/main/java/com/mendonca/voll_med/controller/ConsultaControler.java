package com.mendonca.voll_med.controller;

import com.mendonca.voll_med.dtos.input.AgendamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/consulta")
public class ConsultaControler {

    @PostMapping
    public ResponseEntity agendarConsulta(@RequestBody @Valid AgendamentoConsulta agendamentoConsulta){
        return ResponseEntity.ok().build();
    }
}
