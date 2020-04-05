package com.example.MuQuiz.ApiClasses.Actor;

import java.util.List;

public class ActorsApiReceiver {
    private Long id;
    private List<Actor> cast;

    public ActorsApiReceiver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }
}
