package com.mendonca.voll_med.dtos.input;

public record AtualizacaoPaciente(
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
