package com.example.MuQuiz.ApiClasses;

import java.util.List;

public class ActorsApiReceiver {
    private Long id;
    private List<Actors> cast;

    public ActorsApiReceiver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Actors> getCast() {
        return cast;
    }

    public void setCast(List<Actors> cast) {
        this.cast = cast;
    }
}
