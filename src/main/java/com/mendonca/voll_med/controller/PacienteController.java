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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDto>> listar(@PageableDefault(size = 10) Pageable pageable) {
        var paciente = pacienteService.listarPacientes(pageable);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscar(@PathVariable Long id) {
        var paciente = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> cadastrarPaciente(@RequestBody CadastroPaciente pacienteDto,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        var paciente  =  pacienteService.salvarPaciente(pacienteDto);

        var uri = uriComponentsBuilder.path("/v1/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDto(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> atualizarPaciente(@PathVariable Long id, @RequestBody AtualizacaoPaciente pacienteDto) {
        var paciente =  pacienteService.atualizarPaciente(id, pacienteDto);

        return ResponseEntity.ok(new PacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }

}
