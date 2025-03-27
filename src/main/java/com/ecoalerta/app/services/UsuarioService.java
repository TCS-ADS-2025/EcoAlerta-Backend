package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }
}