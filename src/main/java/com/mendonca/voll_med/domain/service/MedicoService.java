package com.mendonca.voll_med.domain.service;

import com.mendonca.voll_med.domain.repository.MedicoRepository;
import com.mendonca.voll_med.dtos.DetalhamentoMedico;
import com.mendonca.voll_med.dtos.MedicoDto;
import com.mendonca.voll_med.dtos.input.AtualizacaoMedico;
import com.mendonca.voll_med.dtos.input.CadastroMedico;
import com.mendonca.voll_med.exception.MedicoNotFoundException;
import com.mendonca.voll_med.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<MedicoDto> listar(Pageable pageable) {
        var medicos = medicoRepository.findAllByAtivoTrue(pageable);
        return medicos.map(MedicoDto::new);
    }

    public DetalhamentoMedico buscar(Long id) {
        var medico = medicoRepository.findByIdAndAtivoTrue(id).orElseThrow(
                () -> new MedicoNotFoundException("Medico não encontrado")
        );
        return new DetalhamentoMedico(medico);
    }

    @Transactional
    public DetalhamentoMedico cadastrarMedico(CadastroMedico dto) {
        Medico medico = new Medico(dto);
        medico = medicoRepository.save(medico);
        return new DetalhamentoMedico(medico);
    }

    @Transactional
    public DetalhamentoMedico atualizarMedico(Long id, AtualizacaoMedico dto) {
        var medico = medicoRepository.findById(id).orElseThrow(
                () -> new MedicoNotFoundException("Medico não encontrado")
        );
        medico.atualizar(dto);
        return new DetalhamentoMedico(medico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }

}
