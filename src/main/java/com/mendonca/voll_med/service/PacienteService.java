package com.mendonca.voll_med.service;

import com.mendonca.voll_med.dtos.input.AtualizacaoPaciente;
import com.mendonca.voll_med.dtos.input.CadastroPaciente;
import com.mendonca.voll_med.dtos.input.PacienteDto;
import com.mendonca.voll_med.exception.PacienteNotFoundException;
import com.mendonca.voll_med.model.Paciente;
import com.mendonca.voll_med.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Page<PacienteDto> listarPacientes(Pageable paginacao) {
        var pacientes = pacienteRepository.findAllByAtivoTrue(paginacao);
        return pacientes.map(PacienteDto::new);
    }

    public PacienteDto buscarPacientePorId(Long id) {
        var paciente = pacienteRepository.findByIdAndAtivoTrue(id).orElseThrow(
                () -> new PacienteNotFoundException("Paciente não encontrado")
        );
        return new PacienteDto(paciente);
    }

    @Transactional
    public Paciente salvarPaciente(CadastroPaciente dto) {
        Paciente paciente = new Paciente(dto);
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente atualizarPaciente(Long id, AtualizacaoPaciente dto) {
        var paciente = pacienteRepository.findById(id).orElseThrow(
                () -> new PacienteNotFoundException("Paciente não encontrado")
        );
        paciente.atualizar(dto);
        return paciente;
    }

    @Transactional
    public void deletarPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }

}
