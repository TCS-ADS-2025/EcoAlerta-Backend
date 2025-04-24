package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.FilaEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FilaEmailRepository extends JpaRepository<FilaEmail, UUID> {

    List<FilaEmail> findByEnviadoFalse();
}
