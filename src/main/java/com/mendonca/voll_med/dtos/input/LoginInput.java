package com.mendonca.voll_med.dtos.input;

import jakarta.validation.constraints.NotBlank;

public record LoginInput(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
