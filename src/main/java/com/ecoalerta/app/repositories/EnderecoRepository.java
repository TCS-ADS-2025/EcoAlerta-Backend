package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByCep(String cep);
}