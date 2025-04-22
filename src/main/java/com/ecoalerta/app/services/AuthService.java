package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.usuario.LoginRequestDTO;
import com.ecoalerta.app.dto.usuario.LoginResponseDTO;
import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.infra.exceptions.EmailCadastradoException;
import com.ecoalerta.app.infra.exceptions.EmailNaoEnviadoException;
import com.ecoalerta.app.infra.exceptions.UsuarioNaoEncontradoException;
import com.ecoalerta.app.infra.security.TokenService;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final EnderecoService enderecoService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final MensagemRepository mensagemRepository;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO request) {
        Usuario usuario = this.repository.findByEmail(request.email())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new LoginResponseDTO(usuario.getNomeCompleto(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<LoginResponseDTO> register(UsuarioRequestDTO request) {
        Optional<Usuario> usuario = repository.findByEmail(request.email());

        if (usuario.isPresent()) {
            throw new EmailCadastradoException();
        }

        Endereco endereco = enderecoService.buscarEnderecoPorCep(
                request.cep(),
                request.bairroId(),
                request.logradouro(),
                request.numero(),
                request.complemento()
        );

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeCompleto(request.nomeCompleto());
        novoUsuario.setEmail(request.email());
        novoUsuario.setSenha(passwordEncoder.encode(request.senha()));
        novoUsuario.setEndereco(endereco);
        repository.save(novoUsuario);

        String titulo = "Bem-Vindo";
        String mensagemTexto = "Olá " + novoUsuario.getNomeCompleto() + ", \n\n Seja bem-vindo ao sistema Eco Alerta!";
        Boolean status;

        try {
            emailService.enviarEmail(novoUsuario.getEmail(), titulo, mensagemTexto);
            status = true;
        } catch (Exception e) {
            status = false;
            throw new EmailNaoEnviadoException();
        }

        Mensagem mensagem = new Mensagem(
                status,
                titulo,
                novoUsuario.getEmail(),
                mensagemTexto,
                LocalDateTime.now(),
                novoUsuario
        );
        mensagemRepository.save(mensagem);

        String token = tokenService.generateToken(novoUsuario);
        return ResponseEntity.ok(new LoginResponseDTO(novoUsuario.getNomeCompleto(), token));
    }
}