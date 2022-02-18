package com.mkyong.reactive;

import java.util.List;

public class MonoListOfMovies {
    private List<Movie> movieList;

    public MonoListOfMovies(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
