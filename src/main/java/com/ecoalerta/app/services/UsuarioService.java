package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.infra.exceptions.UsuarioNaoEncontradoException;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoService enderecoService;

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public Usuario listarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow((UsuarioNaoEncontradoException::new));

        return usuario;
    }

    public List<UsuarioResponseDTO> listarPorNome(String nomeCompleto) {
        return usuarioRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto)
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public Usuario atualizar(UUID id, UsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow((UsuarioNaoEncontradoException::new));

        Endereco endereco = enderecoService.buscarEnderecoPorCep(
                request.cep(),
                request.bairroId(),
                request.logradouro(),
                request.numero(),
                request.complemento()
        );

        usuario.setNomeCompleto(request.nomeCompleto());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setEndereco(endereco);

        return usuarioRepository.save(usuario);
    }

    public void excluir(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow((UsuarioNaoEncontradoException::new));

        usuarioRepository.delete(usuario);
    }
}