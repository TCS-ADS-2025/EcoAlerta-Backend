package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, UUID> {

    Optional<Bairro> findByNomeBairro(String nomeBairro);

    List<Bairro> findAllByOrderByNomeBairroAsc();


    List<Bairro> findByNomeBairroContainingIgnoreCase(String nomeBairro);
}