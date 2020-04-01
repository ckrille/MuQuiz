package com.example.MuQuiz.questionsPage;


import com.example.MuQuiz.ApiClasses.ActorsMovies;
import com.example.MuQuiz.ApiClasses.Cast;
import com.example.MuQuiz.ApiClasses.Results;
import com.example.MuQuiz.category.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Questions {

    private String theQuestion;
    private List<Results> movieList;
    private List<Cast> castList;
    private Long correctAnswer;

    public Questions() {
    }

    public Questions(String theQuestion, List<Results> movieList, Long correctAnswer) {
        this.theQuestion = theQuestion;
        this.movieList = movieList;
        this.correctAnswer = correctAnswer;
    }

    public Questions getCharacterQuestion(RestTemplate restTemplate){
        Questions questions = new Questions();
        List<Cast> castList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 4; i++) {
            Cast results = categoryService.getRandomMovieCharacter(restTemplate);
            castList.add(results);
        }


        int randForQandA = rand.nextInt(3);
        // correctAnswer = results.getRelease_date();
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " +castList.get(randForQandA).getCharacter());

        questions.theQuestion = "What is the name of the character this actor plays in "+ castList.get(randForQandA).getTitle() +"?";
        questions.correctAnswer = correctAnswer;
        questions.castList = castList;


        return questions;
    }

    public Questions getWhatYearQuestion(RestTemplate restTemplate){

        List<Results> movieList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 4; i++) {
            Results results = categoryService.getRandomMovie(restTemplate);
            movieList.add(results);
        }


        int randForQandA = rand.nextInt(3);
       // correctAnswer = results.getRelease_date();
        String correctReleaseDate = movieList.get(randForQandA).getRelease_date();
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " +movieList.get(randForQandA).getRelease_date());
        Questions questions = new Questions(("What date was "+ movieList.get(randForQandA).getTitle()) +" released?"
                ,movieList
                , correctAnswer);
        return questions;
    }

    public Questions getYearForMovieQuestion(RestTemplate restTemplate){

        List<Results> movieList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 4; i++) {
            Results results = categoryService.getRandomMovie(restTemplate);
            movieList.add(results);
        }

        int randForQandA = rand.nextInt(3);
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " +movieList.get(randForQandA).getTitle());
        Questions questions = new Questions(("Which movie was released " + movieList.get(randForQandA).getRelease_date()) +"?"
                ,movieList
                , correctAnswer);
        return questions;
    }

    public Questions getDescQuestion(RestTemplate restTemplate){

        List<Results> movieList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 3; i++) {
            Results results = categoryService.getRandomMovie(restTemplate);
            movieList.add(results);
        }

        int randForQandA = rand.nextInt(2);
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " +movieList.get(randForQandA).getTitle());
       Questions questions = new Questions(("Vilken film är det enligt beskrivningen? \n" + movieList.get(randForQandA).getOverview())
               ,movieList
               , correctAnswer);
        return questions;
    }

    public Questions getPosterQuestion(RestTemplate restTemplate){
        CategoryService categoryService = new CategoryService();
        Results results = categoryService.getRandomMovie(restTemplate);
        List<Results> movieList = new ArrayList<>();

        movieList.add(results);
        this.theQuestion = "Vilken film är det på bilden?";
        this.correctAnswer = results.getId();

        Questions questions = new Questions("Vilken film är det på bilden? \n"
                ,movieList
                ,correctAnswer);
        return questions;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }

    public List<Results> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Results> movieList) {
        this.movieList = movieList;
    }

    public Long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }
}
