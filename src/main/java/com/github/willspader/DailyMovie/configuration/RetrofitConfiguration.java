package com.github.willspader.DailyMovie.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.willspader.DailyMovie.client.OMDBClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.Dispatcher;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {

    private final ObjectMapper objectMapper;
    private final long readTimeout;
    private final long writeTimeout;
    private final long connectionTimeout;

    public RetrofitConfiguration(final ObjectMapper objectMapper,
                                 @Value("${http.client.read-timeout:10}") final int readTimeout,
                                 @Value("${http.client.write-timeout:10}") final int writeTimeout,
                                 @Value("${http.client.connection-timeout:10}") final int connectionTimeout) {
        this.objectMapper = objectMapper;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    @Bean
    public OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(Executors.newScheduledThreadPool(10)))
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Bean
    public OMDBClient omdbClient(@Value("${omdb.api.url}") final String url) {
        return this.createBuilder(url).create(OMDBClient.class);
    }

    private Retrofit createBuilder(final String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(this.createOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

}