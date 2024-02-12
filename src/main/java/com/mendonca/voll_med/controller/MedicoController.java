package com.mendonca.voll_med.controller;

import com.mendonca.voll_med.dtos.DetalhamentoMedico;
import com.mendonca.voll_med.dtos.MedicoDto;
import com.mendonca.voll_med.dtos.input.AtualizacaoMedico;
import com.mendonca.voll_med.dtos.input.CadastroMedico;
import com.mendonca.voll_med.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoRepository) {
        this.medicoService = medicoRepository;
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDto>> listar(@PageableDefault(size = 10) Pageable pageable) {
        var medicos = medicoService.listar(pageable);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoMedico> buscar(@PathVariable Long id) {
        var medico = medicoService.buscar(id);
        return ResponseEntity.ok(medico);
    }

    @PostMapping
    public ResponseEntity<DetalhamentoMedico> cadastrarMedico(@Valid @RequestBody CadastroMedico dadosCadastroMedico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var medico = medicoService.cadastrarMedico(dadosCadastroMedico);
        var uri = uriComponentsBuilder.path("/v1/medicos/{id}").buildAndExpand(medico.id()).toUri();

        return ResponseEntity.created(uri).body(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhamentoMedico> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizacaoMedico dto) {
        var medico = medicoService.atualizarMedico(id, dto);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
