package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.cronograma.CronogramaRequestDTO;
import com.ecoalerta.app.dto.cronograma.CronogramaResponseDTO;
import com.ecoalerta.app.infra.exceptions.CronogramaSemDiaSemanaException;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.models.enums.DiaSemana;
import com.ecoalerta.app.repositories.BairroRepository;
import com.ecoalerta.app.repositories.CronogramaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CronogramaService {

    private final CronogramaRepository cronogramaRepository;
    private final BairroRepository bairroRepository;

    public Cronograma criar(CronogramaRequestDTO request){
        Cronograma cronograma = new Cronograma();
        cronograma.setDiaSemana(request.diaSemana());

        List<Bairro> bairros = bairroRepository.findAllById(request.bairrosIds());
        bairros.forEach(bairro -> bairro.setCronograma(cronograma));

        return cronogramaRepository.save(cronograma);
    }

    public List<CronogramaResponseDTO> listarTodos(){
        return cronogramaRepository.findAll()
                .stream()
                .map(CronogramaResponseDTO::fromEntity)
                .toList();
    }

    public List<CronogramaResponseDTO> listarPorDiaSemana(DiaSemana diaSemana) {
        List<Cronograma> cronogramas = cronogramaRepository.findByDiaSemana(diaSemana);

        if (cronogramas.isEmpty()){
            throw new CronogramaSemDiaSemanaException();
        }

        return cronogramas.stream()
                .map(CronogramaResponseDTO::fromEntity)
                .toList();
    }

    public Cronograma atualizar(UUID id, CronogramaRequestDTO request) {
        Cronograma cronograma = cronogramaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cronograma não encontrado"));

        cronograma.setDiaSemana(request.diaSemana());

        cronograma.getBairros().forEach(bairro -> bairro.setCronograma(null));

        List<Bairro> bairros = bairroRepository.findAllById(request.bairrosIds());
        bairros.forEach(bairro -> bairro.setCronograma(cronograma));

        return cronogramaRepository.save(cronograma);
    }

    public void excluir(UUID id) {
        Cronograma cronograma = cronogramaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cronograma não encontrado"));

        cronograma.getBairros().forEach(bairro -> bairro.setCronograma(null));
        bairroRepository.saveAll(cronograma.getBairros());

        cronogramaRepository.delete(cronograma);
    }
}