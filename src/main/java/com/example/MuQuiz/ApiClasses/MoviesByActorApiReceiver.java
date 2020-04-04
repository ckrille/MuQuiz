package com.example.MuQuiz.ApiClasses;

import java.util.List;

public class MoviesByActorApiReceiver {

    private List<MoviesByActor> cast;

    public MoviesByActorApiReceiver() {
    }

    public List<MoviesByActor> getActorsMovies() {
        return cast;
    }

    public void setActorsMovies(List<MoviesByActor> actorsMovies) {
        this.cast = cast;
    }
}
