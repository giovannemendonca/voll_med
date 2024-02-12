package com.mendonca.voll_med.service;

import com.mendonca.voll_med.dtos.MedicoDto;
import com.mendonca.voll_med.dtos.input.AtualizacaoMedico;
import com.mendonca.voll_med.dtos.input.CadastroMedico;
import com.mendonca.voll_med.exception.MedicoNotFoundException;
import com.mendonca.voll_med.model.Medico;
import com.mendonca.voll_med.repository.MedicoRepository;
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

    public MedicoDto buscar(Long id) {
        var medico = medicoRepository.findByIdAndAtivoTrue(id).orElseThrow(
                () -> new MedicoNotFoundException("Medico não encontrado")
        );
        return new MedicoDto(medico);
    }

    @Transactional
    public Medico cadastrarMedico(CadastroMedico dto) {
        Medico medico = new Medico(dto);
        return medicoRepository.save(medico);
    }

    @Transactional
    public void atualizarMedico(Long id, AtualizacaoMedico dto) {
        var medico = medicoRepository.findById(id).orElseThrow(
                () -> new MedicoNotFoundException("Medico não encontrado")
        );
        medico.atualizar(dto);

    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }

}
