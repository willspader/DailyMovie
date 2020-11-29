package com.github.willspader.DailyMovie.service;

import com.github.willspader.DailyMovie.client.caller.OMDBCaller;
import com.github.willspader.DailyMovie.dto.OmdbDTO;
import com.github.willspader.DailyMovie.model.Historic;
import com.github.willspader.DailyMovie.model.Random;
import com.github.willspader.DailyMovie.repository.RandomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RandomService {

    private final RandomRepository randomRepository;
    private final OMDBCaller omdbCaller;
    private final HistoricService historicService;

    public RandomService(final RandomRepository randomRepository,
                         final OMDBCaller omdbCaller,
                         final HistoricService historicService) {
        this.randomRepository = randomRepository;
        this.omdbCaller = omdbCaller;
        this.historicService = historicService;
    }

    public OmdbDTO getRandomMovieOrTVShow() {
        List<Random> all = randomRepository.findAll();

        if (all.size() > 0) {
            OmdbDTO movieById = this.omdbCaller.getMovieById(all.get(0).getRandomId());

            randomRepository.delete(all.get(0));

            // we open a thread to save the title in the historic table, so the user don't need to wait too much for the result
            CompletableFuture<Void> completableFuture = new CompletableFuture<>();
            completableFuture.completeAsync(() -> {
                Historic historic = new Historic();
                historic.setName(movieById.getTitle());
                this.historicService.save(historic);

                return null;
            });

            return movieById;
        }
        return null;
    }

}
