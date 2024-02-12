package com.mendonca.voll_med.dtos;

import com.mendonca.voll_med.model.Especialidade;
import com.mendonca.voll_med.model.Medico;

public record MedicoDto(Long id,String nome, String email, String crm, Especialidade especialidade) {

    public MedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
