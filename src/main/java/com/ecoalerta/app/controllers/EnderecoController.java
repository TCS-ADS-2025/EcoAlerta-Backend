package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.endereco.EnderecoResponseDTO;
import com.ecoalerta.app.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoResponseDTO>> listarTodos() {
        List<EnderecoResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }
}