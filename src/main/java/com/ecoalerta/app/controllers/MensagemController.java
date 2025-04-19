package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.services.MensagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@RequestBody @Valid MensagemRequestDTO request) {
        Mensagem cadastrada = service.criar(request);
        return ResponseEntity.ok("Mensagem cadastrada com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MensagemResponseDTO>> listarTodos() {
        List<MensagemResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MensagemResponseDTO> listarPorId(@PathVariable UUID id) {
        Mensagem response = service.listarPorId(id);
        return ResponseEntity.ok(MensagemResponseDTO.fromEntity(response));
    }

    @GetMapping("listar/usuario/{id}")
    public ResponseEntity<List<MensagemResponseDTO>> listarMensagensPorUsuario(@PathVariable UUID id) {
        return ResponseEntity.ok(service.listarPorUsuario(id));
    }
}