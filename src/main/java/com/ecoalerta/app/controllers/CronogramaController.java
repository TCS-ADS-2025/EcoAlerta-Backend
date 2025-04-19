package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.cronograma.CronogramaRequestDTO;
import com.ecoalerta.app.dto.cronograma.CronogramaResponseDTO;
import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.models.enums.DiaSemana;
import com.ecoalerta.app.services.CronogramaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cronogramas")
@RequiredArgsConstructor
public class CronogramaController {

    private final CronogramaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> criar(@Valid @RequestBody CronogramaRequestDTO request){
        Cronograma cadastrado = service.criar(request);
        return ResponseEntity.ok("Cronograma cadastrado com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CronogramaResponseDTO>> listarTodos(){
        List<CronogramaResponseDTO> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/dia-da-semana/{dia}")
    public ResponseEntity<List<CronogramaResponseDTO>> listarPorDiaSemana(@PathVariable("dia") DiaSemana diaSemana) {
        List<CronogramaResponseDTO> response = service.listarPorDiaSemana(diaSemana);
        return ResponseEntity.ok(service.listarPorDiaSemana(diaSemana));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CronogramaResponseDTO> atualizar(@Valid
                                                           @PathVariable UUID id,
                                                           @RequestBody CronogramaRequestDTO request) {
        CronogramaResponseDTO atualizado = CronogramaResponseDTO.fromEntity(service.atualizar(id, request));
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable UUID id){
        service.excluir(id);
        return ResponseEntity.ok("Cronograma excluído com sucesso");
    }
}