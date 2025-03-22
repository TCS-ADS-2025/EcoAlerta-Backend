package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.services.MensagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@RequestBody @Valid MensagemRequestDTO request) {
        MensagemResponseDTO response = service.criar(request);
        return ResponseEntity.ok("Mensagem cadastrada com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MensagemResponseDTO>> listarTodos() {
        List<MensagemResponseDTO> mensagens = service.listarTodos();
        return ResponseEntity.ok(mensagens);
    }
}
