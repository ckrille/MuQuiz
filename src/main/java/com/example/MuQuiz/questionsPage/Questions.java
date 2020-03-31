package com.example.MuQuiz.questionsPage;

import com.example.MuQuiz.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class Questions {

    private String theQuestion;
    private List<Movie> movieList;
    private String APIkey = "21c95e422f1845eea7a7274d9e67524b";
    private Long movieId;

    public Questions() {
    }

    public Questions(String theQuestion, List<Movie> movieList) {
        this.theQuestion = theQuestion;
        this.movieList = movieList;
    }


    public List<Movie> questionDesc(RestTemplate restTemplate) {
        movieId = 100L;
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + (movieId + i) + "?api_key=" + APIkey + "&language=en-US", Movie.class);
            movieList.add(movie);
        }

        this.theQuestion = "Vilken film är det enligt beskrivningen? \n" + movieList.get(0).overview;
        return movieList;
    }

    public List<Movie> questionName(RestTemplate restTemplate) {
        movieId = 100L;
        List<Movie> movieList = new ArrayList<>();
        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + (movieId + 5) + "?api_key=" + APIkey + "&language=en-US", Movie.class);
        movieList.add(movie);
        this.theQuestion = "Vilken film är det på bilden?";
        return movieList;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
