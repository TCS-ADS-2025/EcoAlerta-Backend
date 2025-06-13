package com.ecoalerta.app.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bairros/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bairros/listar/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bairros/listar/nome/{nome}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comentarios/cadastrar").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/comentarios/listar").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/comentarios/listar/categoria/{categoriaComentario}").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/comentarios/listar/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/comentarios/excluir/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cronogramas/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cronogramas/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cronogramas/listar/dia-da-semana/{dia}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/cronogramas/atualizar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cronogramas/excluir/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/enderecos/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/mensagens/cadastrar/todos-usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/mensagens/cadastrar/bairros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/mensagens/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/mensagens/listar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/mensagens/listar/usuario/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/listar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/perfil").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/usuarios/listar/nome/{nome}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/atualizar/{id}").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/excluir/{id}").hasRole("USUARIO")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}