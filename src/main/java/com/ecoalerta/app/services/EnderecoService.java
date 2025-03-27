package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.viacep.ViaCepResponseDTO;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.repositories.BairroRepository;
import com.ecoalerta.app.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final BairroRepository bairroRepository;
    private final EnderecoRepository enderecoRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Endereco buscarEnderecoPorCep(String cep,
                                         UUID bairroId,
                                         String logradouro,
                                         String numero,
                                         String complemento) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepResponseDTO response = restTemplate.getForObject(url, ViaCepResponseDTO.class);

        if (response != null && response.getCep() != null) {
            String selecionaLogradouro = (response.getLogradouro() != null && !response.getLogradouro().isEmpty()) ?
                    response.getLogradouro() : logradouro;

            Bairro bairro = bairroRepository.findById(bairroId)
                    .orElseThrow(() -> new RuntimeException("Bairro não encontrado"));

            Endereco endereco = new Endereco();
            endereco.setCep(response.getCep());
            endereco.setLogradouro(selecionaLogradouro);
            endereco.setLocalidade(response.getLocalidade());
            endereco.setUf(response.getUf());
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);

            return endereco;
        }
        throw new RuntimeException("CEP inválido ou não encontrado!");
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }
}