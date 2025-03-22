package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.repositories.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository repository;

    public MensagemResponseDTO criar(MensagemRequestDTO request){
        Mensagem mensagem = request.toEntity();

        Mensagem salvo = repository.save(mensagem);

        return MensagemResponseDTO.fromEntity(salvo);
    }

    public List<MensagemResponseDTO> listarTodos(){
        return repository.findAll()
                .stream()
                .map(MensagemResponseDTO::fromEntity)
                .toList();
    }
}
