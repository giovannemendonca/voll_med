package com.mendonca.voll_med.controller;

import com.mendonca.voll_med.dtos.input.AtualizacaoPaciente;
import com.mendonca.voll_med.dtos.input.CadastroPaciente;
import com.mendonca.voll_med.dtos.input.PacienteDto;
import com.mendonca.voll_med.model.Paciente;
import com.mendonca.voll_med.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public Page<PacienteDto> listar(@PageableDefault(size = 10) Pageable pageable) {
        return pacienteService.listarPacientes(pageable);
    }

    @GetMapping("/{id}")
    public PacienteDto buscar(@PathVariable Long id) {
        return pacienteService.buscarPacientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente cadastrarPaciente(@RequestBody CadastroPaciente pacienteDto) {
        return pacienteService.salvarPaciente(pacienteDto);
    }

    @PutMapping("/{id}")
    public void atualizarPaciente(@PathVariable Long id, @RequestBody AtualizacaoPaciente pacienteDto) {
        pacienteService.atualizarPaciente(id, pacienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }

}
