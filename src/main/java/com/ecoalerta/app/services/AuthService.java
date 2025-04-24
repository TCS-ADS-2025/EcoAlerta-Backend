package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.FilaEmail.FilaEmailDTO;
import com.ecoalerta.app.dto.usuario.LoginRequestDTO;
import com.ecoalerta.app.dto.usuario.LoginResponseDTO;
import com.ecoalerta.app.dto.usuario.UsuarioRequestDTO;
import com.ecoalerta.app.infra.exceptions.EmailCadastradoException;
import com.ecoalerta.app.infra.security.TokenService;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.models.enums.Role;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final EnderecoService enderecoService;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final MensagemRepository mensagemRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO request) {
        if (this.repository.findByEmail(request.email()) == null) {
            throw new EmailCadastradoException();
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Transactional
    public ResponseEntity<LoginResponseDTO> register(UsuarioRequestDTO request) {

        if (this.repository.findByEmail(request.email()) != null) {
            throw new EmailCadastradoException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.senha());

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
        novoUsuario.setSenha(encryptedPassword);
        novoUsuario.setEndereco(endereco);
        novoUsuario.setRole(Role.USUARIO);
        repository.save(novoUsuario);

        String titulo = "Bem-Vindo";
        String mensagemTexto = "Olá " + novoUsuario.getNomeCompleto() +
                ", \n\nSeja bem-vindo ao sistema Eco Alerta!";

        Mensagem mensagem = new Mensagem(
                false,
                titulo,
                novoUsuario.getEmail(),
                mensagemTexto,
                LocalDateTime.now(),
                novoUsuario
        );
        mensagemRepository.save(mensagem);

        emailService.enfileirarEmail(new FilaEmailDTO(mensagem));

        String token = tokenService.generateToken(novoUsuario);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}