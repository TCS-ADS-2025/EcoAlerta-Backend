package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    List<Usuario> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);

    List<Usuario> findByEnderecoBairroIn(List<Bairro> bairros);

    UserDetails findByEmail(String email);
}