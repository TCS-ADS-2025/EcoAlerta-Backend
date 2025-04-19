package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.dto.usuario.UsuarioResponseDTO;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        String titulo = "Bem-vindo";
        String mensagemTexto = "Olá " + usuario.getNomeCompleto() + ", seja vem-vindo ao sistema Eco Alerta!";
        Boolean status;

        try {
            emailService.enviarEmail(usuario.getEmail(), titulo, mensagemTexto);
            status = true;
        } catch (Exception e) {
            status = false;
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

        return usuario;
    }

    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }
}