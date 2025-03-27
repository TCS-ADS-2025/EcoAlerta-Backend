package com.ecoalerta.app.dto.viacep;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepResponseDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}