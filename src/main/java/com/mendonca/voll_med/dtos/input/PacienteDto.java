package com.mendonca.voll_med.dtos.input;

import com.mendonca.voll_med.model.Paciente;

public record PacienteDto(
        Long id,
        String nome,
        String email,
        String cpf
) {

    public PacienteDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
