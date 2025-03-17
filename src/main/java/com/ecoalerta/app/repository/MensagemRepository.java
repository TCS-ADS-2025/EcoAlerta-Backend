package com.ecoalerta.app.repository;

import com.ecoalerta.app.models.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
