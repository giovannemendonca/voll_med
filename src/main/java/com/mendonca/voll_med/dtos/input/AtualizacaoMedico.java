package com.mendonca.voll_med.dtos.input;

public record AtualizacaoMedico(
        String nome,
        String telefone,
        String email,
        DadosEndereco dadosEndereco
        ) {
}
