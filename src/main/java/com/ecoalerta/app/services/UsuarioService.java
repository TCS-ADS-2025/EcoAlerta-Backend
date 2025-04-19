package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.infra.exceptions.EmailNaoEnviadoException;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final MensagemRepository mensagemRepository;
    private final EnderecoService enderecoService;
    private final EmailService emailService;

    public Usuario criar(UsuarioRequestDTO request) {
        Endereco endereco = enderecoService.buscarEnderecoPorCep(
                request.cep(),
                request.bairroId(),
                request.logradouro(),
                request.numero(),
                request.complemento());

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(request.nomeCompleto());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);

        String titulo = "Bem-Vindo";
        String mensagemTexto = "Olá " + usuario.getNomeCompleto() + ", seja bem-vindo ao sistema Eco Alerta!";
        Boolean status;

        try {
            emailService.enviarEmail(usuario.getEmail(), titulo, mensagemTexto);
            status = true;
        } catch (Exception e) {
            status = false;
            throw new EmailNaoEnviadoException();
        }

        Mensagem mensagem = new Mensagem(
                status,
                titulo,
                usuario.getEmail(),
                mensagemTexto,
                LocalDateTime
                        .parse(LocalDateTime.now()
                        .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd'T'HH:mm:ss"))),
                usuario
        );
        mensagemRepository.save(mensagem);

        return null;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public Usuario listarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return usuario;
    }

    public List<UsuarioResponseDTO> listarPorNome(String nomeCompleto) {
        return usuarioRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto)
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public Usuario atualizar(UUID id, UsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

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
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}