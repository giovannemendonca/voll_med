package com.mendonca.voll_med.controller;

import com.mendonca.voll_med.dtos.DadosToken;
import com.mendonca.voll_med.config.security.TokenService;
import com.mendonca.voll_med.domain.model.Usuario;
import com.mendonca.voll_med.dtos.input.LoginInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AutheticationController {

    private AuthenticationManager manager;

    private TokenService tokenService;

    public AutheticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DadosToken> login(@RequestBody @Valid LoginInput loginInput) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginInput.login(), loginInput.senha());
        var authetication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((Usuario) (authetication.getPrincipal()));

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }
}
