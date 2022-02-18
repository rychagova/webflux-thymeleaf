package com.mkyong.reactive;

import reactor.core.publisher.Mono;

public class Cinema {
    String string;
    Mono<MonoListOfMovies> listOfMoviesMono;

    public Cinema(String string, Mono<MonoListOfMovies> listOfMoviesMono) {
        this.string = string;
        this.listOfMoviesMono = listOfMoviesMono;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Mono<MonoListOfMovies> getListOfMoviesMono() {
        return listOfMoviesMono;
    }

    public void setListOfMoviesMono(Mono<MonoListOfMovies> listOfMoviesMono) {
        this.listOfMoviesMono = listOfMoviesMono;
    }
}
