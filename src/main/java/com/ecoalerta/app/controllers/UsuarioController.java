package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@RequestBody @Valid UsuarioRequestDTO request) {
        Usuario response = service.criar(request);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = service.listar();
        return ResponseEntity.ok(usuarios);
    }
}