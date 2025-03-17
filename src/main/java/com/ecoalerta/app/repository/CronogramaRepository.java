package com.ecoalerta.app.repository;

import com.ecoalerta.app.models.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {
}
