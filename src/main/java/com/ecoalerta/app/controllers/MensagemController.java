package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.mensagem.MensagemBairrosRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemTodosUsuariosRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.repositories.BairroRepository;
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

    private final BairroRepository bairroRepository;
    private final MensagemService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@RequestBody @Valid MensagemRequestDTO request) {
        Mensagem cadastrada = service.criar(request);
        return ResponseEntity.ok("Mensagem cadastrada com sucesso!");
    }

    @PostMapping("/cadastrar/todos-usuarios")
    public ResponseEntity<String> enviarParaTodosUsuarios(@Valid @RequestBody MensagemTodosUsuariosRequestDTO request) {
        service.enviarParaTodosUsuarios(request.titulo(), request.mensagem());
        return ResponseEntity.ok("Mensagens enviadas para todos os usuários.");
    }

    @PostMapping("/cadastrar/bairros")
    public ResponseEntity<String> enviarParaBairros(@Valid @RequestBody MensagemBairrosRequestDTO request) {
        List<Bairro> bairros = bairroRepository.findAllById(request.bairrosIds());
        service.enviarParaBairros(bairros, request.titulo(), request.mensagem());
        return ResponseEntity.ok("Mensagens enviadas para os bairros informados.");
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