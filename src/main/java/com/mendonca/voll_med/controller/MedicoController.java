package com.mendonca.voll_med.controller;

import com.mendonca.voll_med.dtos.input.AtualizacaoMedico;
import com.mendonca.voll_med.dtos.input.CadastroMedico;
import com.mendonca.voll_med.dtos.MedicoDto;
import com.mendonca.voll_med.model.Medico;
import com.mendonca.voll_med.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoRepository) {
        this.medicoService = medicoRepository;
    }

    @GetMapping
    public Page<MedicoDto> listar(@PageableDefault(size = 10) Pageable pageable) {
        return medicoService.listar(pageable);
    }

    @GetMapping("/{id}")
    MedicoDto buscar(@PathVariable Long id) {
        return medicoService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medico cadastrarMedico(@Valid @RequestBody CadastroMedico dadosCadastroMedico) {
        return medicoService.cadastrarMedico(dadosCadastroMedico);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @Valid @RequestBody AtualizacaoMedico dto) {
        medicoService.atualizarMedico(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        medicoService.deletarMedico(id);
    }
}
