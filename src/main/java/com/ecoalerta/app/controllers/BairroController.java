package com.ecoalerta.app.controllers;

import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.services.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Bairro>> listarTodos(){
        List<Bairro> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Bairro> listarPorId(@PathVariable UUID id) {
        Bairro response = service.listarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/nome/{nome}")
    public ResponseEntity<List<Bairro>> listarPorNome(@PathVariable("nome") String nomeBairro) {
        List<Bairro> response = service.listarPorNome(nomeBairro);
        return ResponseEntity.ok(response);
    }
}