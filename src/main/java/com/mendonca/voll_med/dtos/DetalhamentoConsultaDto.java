package com.mendonca.voll_med.dtos;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDto(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime date
) {
}
