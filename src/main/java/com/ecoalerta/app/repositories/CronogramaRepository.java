package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.models.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, UUID> {

    List<Cronograma> findByDiaSemana(DiaSemana diaSemana);
}