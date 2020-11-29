package com.github.willspader.DailyMovie.service;

import com.github.willspader.DailyMovie.dto.HistoricDTO;
import com.github.willspader.DailyMovie.model.Historic;
import com.github.willspader.DailyMovie.repository.HistoricRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricService {

    private final HistoricRepository historicRepository;

    public HistoricService(final HistoricRepository historicRepository) {
        this.historicRepository = historicRepository;
    }

    public void save(Historic historic) {
        this.historicRepository.save(historic);
    }

    public List<HistoricDTO> findLastSuggestions(final Integer limit) {
        List<Historic> lastSuggestions = this.historicRepository.findLastSuggestions(limit);

        List<HistoricDTO> historicDTOS = new ArrayList<>();
        lastSuggestions.forEach(historic -> historicDTOS.add(new HistoricDTO(historic.getName())));

        return historicDTOS;
    }

}
