package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.usuario.LoginRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO request) {
        return service.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRequestDTO request) {
        return service.register(request);
    }
}