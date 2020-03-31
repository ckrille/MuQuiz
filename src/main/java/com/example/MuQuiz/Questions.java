package com.example.MuQuiz;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Questions {

    public boolean adult;
    String backdrop_path;
    String belongs_to_collection;
    public int budget;
    public String overview;
    public String poster_path;

    String APIkey = "21c95e422f1845eea7a7274d9e67524b";
    int movieId;

    public Movie questionDesc(RestTemplate restTemplate) {
        movieId = 100;
        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + APIkey + "&language=en-US", Movie.class);

        return movie;
    }
}
