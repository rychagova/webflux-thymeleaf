package com.mkyong.reactive.repository;

import com.mkyong.reactive.MonoListOfMovies;
import com.mkyong.reactive.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReactiveMovieRepository implements MovieRepository {

    private static List<Movie> movie = new ArrayList<>();

    static {
        movie.add(new Movie("Polar (2019)", 64));
        movie.add(new Movie("Iron Man (2008)", 79));
        movie.add(new Movie("The Shawshank Redemption (1994)", 93));
        movie.add(new Movie("Forrest Gump (1994)", 83));
        movie.add(new Movie("Glass (2019)", 70));
    }

    @Override
    public Flux<Movie> findAll() {
        //simulate stream data with 2 seconds delay.
        return Flux.fromIterable(movie).delayElements(Duration.ofSeconds(2));
    }
    @Override
    public Mono<Movie> findOne() {
        //simulate stream data with 2 seconds delay.
        return Mono.just(movie.get(0));
    }

    @Override
    public Mono<List<Movie>> findListOfMoviesAsMono() {
        return Mono.just(movie);
    }
    @Override
    public Mono<MonoListOfMovies>  findMonoListOfMoviesAsMono() {
        MonoListOfMovies monoListOfMovies = new MonoListOfMovies(movie);
        return Mono.just(monoListOfMovies);
    }

}
