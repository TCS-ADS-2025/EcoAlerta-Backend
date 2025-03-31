package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.cronograma.CronogramaRequestDTO;
import com.ecoalerta.app.dto.cronograma.CronogramaResponseDTO;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.repositories.BairroRepository;
import com.ecoalerta.app.repositories.CronogramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CronogramaService {

    private final CronogramaRepository cronogramaRepository;
    private final BairroRepository bairroRepository;

    public CronogramaResponseDTO criar(CronogramaRequestDTO request){

        Cronograma cronograma = new Cronograma();
        cronograma.setDiaSemana(request.diaSemana());

        List<Bairro> bairros = bairroRepository.findAllById(request.bairrosIds());

        bairros.forEach(bairro -> bairro.setCronograma(cronograma));

        Cronograma salvo = cronogramaRepository.save(cronograma);

        return CronogramaResponseDTO.fromEntity(salvo);
    }

    public List<CronogramaResponseDTO> listarTodos(){
        return cronogramaRepository.findAll()
                .stream()
                .map(CronogramaResponseDTO::fromEntity)
                .toList();
    }
}
