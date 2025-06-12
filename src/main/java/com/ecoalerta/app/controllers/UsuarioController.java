package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarPorId(@PathVariable UUID id) {
        Usuario response = service.listarPorId(id);
        return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(response));
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> perfil(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = service.listarPorEmail(userDetails.getUsername());
        return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(usuario));
    }

    @GetMapping("/listar/nome/{nome}")
    public ResponseEntity<List> listarPorNome(@PathVariable("nome") String nomeCompleto){
        return ResponseEntity.ok(service.listarPorNome(nomeCompleto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@Valid
                                                        @PathVariable UUID id,
                                                        @RequestBody UsuarioRequestDTO request) {
        Usuario atualizado = service.atualizar(id, request);
        return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(atualizado));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.ok("Usuário excluído com sucesso");
    }
}