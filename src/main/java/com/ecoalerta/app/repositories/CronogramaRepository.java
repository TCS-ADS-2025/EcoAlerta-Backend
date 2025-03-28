package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, UUID> {
}