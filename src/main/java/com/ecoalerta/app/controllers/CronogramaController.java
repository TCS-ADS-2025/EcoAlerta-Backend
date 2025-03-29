package com.ecoalerta.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronogramas")
@RequiredArgsConstructor
public class CronogramaController {

    private final CronogramaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar (@RequestBody @Valid CronogragramaRequestDTO request) {
        CronogramaResponseDTO response = service.criar(request);
        return ResponseEntity.ok("Cronograma cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CronogramaResponseDTO>> listarTodos(){
        List<CronogramaResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }
}
