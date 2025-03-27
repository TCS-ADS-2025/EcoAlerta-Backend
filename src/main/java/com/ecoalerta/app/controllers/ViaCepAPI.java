package com.ecoalerta.app.controllers;

import com.ecoalerta.app.dto.viacep.ViaCepResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consulta-cep")
public class ViaCepAPI {

    @GetMapping("{cep}")
    public ViaCepResponseDTO consultaCep(@PathVariable("cep") String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViaCepResponseDTO> response = restTemplate.
                getForEntity(
                        String.format("https://viacep.com.br/ws/%s/json/", cep),
                        ViaCepResponseDTO.class);
        return response.getBody();
    }
}