package com.ecoalerta.app.repository;

import com.ecoalerta.app.models.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, UUID> {
}
