package com.example.MuQuiz;

import java.util.List;

public class Genre {
   public List<Genres> genres;

    public Genre() {
    }

    public Genre(List<Genres> genres) {
        this.genres = genres;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }
}
