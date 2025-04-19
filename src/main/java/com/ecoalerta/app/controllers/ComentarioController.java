package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.comentario.ComentarioRequestDTO;
import com.ecoalerta.app.dto.comentario.ComentarioResponseDTO;
import com.ecoalerta.app.models.Comentario;
import com.ecoalerta.app.models.enums.CategoriaComentario;
import com.ecoalerta.app.services.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@Valid @RequestBody ComentarioRequestDTO request) {
        Comentario cadastrado = service.criar(request);
        return ResponseEntity.ok("Comentário cadastrado com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ComentarioResponseDTO>> listarTodos() {
        List<ComentarioResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/categoria/{categoriaComentario}")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorCategoria(@PathVariable CategoriaComentario categoriaComentario) {
        List<ComentarioResponseDTO> response = service.listarPorCategoria(categoriaComentario);
        return ResponseEntity.ok(service.listarPorCategoria(categoriaComentario));
    }

    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorUsuario(@PathVariable("id") UUID usuarioId) {
        List<ComentarioResponseDTO> response = service.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.ok("Comentário excluído com sucesso");
    }
}