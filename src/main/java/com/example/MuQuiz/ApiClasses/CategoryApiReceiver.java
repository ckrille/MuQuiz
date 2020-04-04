package com.example.MuQuiz.ApiClasses;

import java.util.List;

public class CategoryApiReceiver {
   private List<Category> genres;

    public CategoryApiReceiver() {
    }

    public CategoryApiReceiver(List<Category> genres) {
        this.genres = genres;
    }

    public List<Category> getGenres() {
        return genres;
    }

    public void setGenres(List<Category> genres) {
        this.genres = genres;
    }
}
