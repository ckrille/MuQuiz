package com.example.MuQuiz.ApiClasses;

import java.util.List;

public class ActorsMoviesAPI {

    public List<ActorsMovies> cast;

    public ActorsMoviesAPI() {
    }

    public List<ActorsMovies> getActorsMovies() {
        return cast;
    }

    public void setActorsMovies(List<ActorsMovies> actorsMovies) {
        this.cast = cast;
    }
}
