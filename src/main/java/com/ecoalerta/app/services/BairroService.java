package com.ecoalerta.app.services;

import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.repositories.BairroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository repository;

    public List<Bairro> listarTodos() {
        return repository.findAll();
    }

    public Bairro listarPorId(UUID id){
        Bairro bairro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bairro não encontrado"));

        return bairro;
    }

    public List<Bairro> listarPorNome(String nomeBairro) {
        return repository.findByNomeBairroContainingIgnoreCase(nomeBairro);
    }
}