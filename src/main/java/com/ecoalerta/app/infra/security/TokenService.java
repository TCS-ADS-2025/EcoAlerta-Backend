package com.ecoalerta.app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ecoalerta.app.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("Eco Alerta")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().toString())
                    .withClaim("userId", usuario.getId().toString())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro durante a autenticação", exception);
        }
    }

    public UUID extractUserIdAsUuid(String token) {
        try {
            String userIdStr = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("Eco Alerta")
                    .build()
                    .verify(token)
                    .getClaim("userId").asString();
            return UUID.fromString(userIdStr);
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            return null;
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Eco Alerta")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractRole(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Eco Alerta")
                    .build()
                    .verify(token)
                    .getClaim("role").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Eco Alerta")
                    .build()
                    .verify(token)
                    .getClaim("userId").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}