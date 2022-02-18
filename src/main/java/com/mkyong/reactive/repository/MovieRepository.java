package com.mkyong.reactive.repository;

import com.mkyong.reactive.MonoListOfMovies;
import com.mkyong.reactive.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieRepository {

    Flux<Movie> findAll();
    Mono<Movie> findOne();

    Mono<List<Movie>> findListOfMoviesAsMono();
    Mono<MonoListOfMovies> findMonoListOfMoviesAsMono();
}
