package com.ecoalerta.app.repositories;

import com.ecoalerta.app.models.Comentario;
import com.ecoalerta.app.models.enums.CategoriaComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

    List<Comentario> findByCategoriaComentario(CategoriaComentario categoriaComentario);

    List<Comentario> findByUsuarioId(UUID usuarioId);
}