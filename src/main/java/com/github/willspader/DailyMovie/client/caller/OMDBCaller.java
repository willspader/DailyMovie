package com.github.willspader.DailyMovie.client.caller;

import com.github.willspader.DailyMovie.client.OMDBClient;
import com.github.willspader.DailyMovie.dto.OmdbDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OMDBCaller {

    private final OMDBClient omdbClient;
    private final String apiKey;

    public OMDBCaller(final OMDBClient omdbClient,
                      @Value("${omdb.api.key}") final String apiKey) {
        this.omdbClient = omdbClient;
        this.apiKey = apiKey;
    }

    public OmdbDTO getMovieById(String id) {
        try {
            return this.omdbClient.getById(id, this.apiKey).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
