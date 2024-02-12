package com.mendonca.voll_med.dtos;

import com.mendonca.voll_med.domain.model.Endereco;
import com.mendonca.voll_med.domain.model.Especialidade;
import com.mendonca.voll_med.domain.model.Medico;

public record DetalhamentoMedico(
        Long id,
        String nome,
        String crm,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {

    public DetalhamentoMedico(Medico dto) {
        this(
                dto.getId(),
                dto.getNome(),
                dto.getCrm(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEspecialidade(),
                dto.getEndereco()
        );
    }
}
