package com.example.MuQuiz.questionsPage;


import com.example.MuQuiz.ApiClasses.*;
import com.example.MuQuiz.ApiClasses.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.ManyToOne;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Questions {

    private String theQuestion;
    private List<Results> movieList;
    private List<Cast> castList;
    private List<ActorsMovies> actorsMoviesList;
    private Long correctAnswer;
    private int randForQandA;

    public Questions() {
    }

    public Questions(String theQuestion, List<Results> movieList, Long correctAnswer) {
        this.theQuestion = theQuestion;
        this.movieList = movieList;
        this.correctAnswer = correctAnswer;
    }

    //fråga utkommenterad, kanske använda framöver

  /*  public Questions getActorNotInMovie(RestTemplate restTemplate){
        Questions questions = new Questions();
        List<ActorsMovies> actorsMoviesList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        ActorsMoviesAPI credits = categoryService.getRandomActorCredits(restTemplate);

        int randSort = rand.nextInt(3);
        for (int i = 0; i < 4; i++) {
            if(randSort == i){
                ActorsMovies wrongAnswer = categoryService.getRandomActorCredit(restTemplate);
                actorsMoviesList.add(wrongAnswer);
            } else {
                actorsMoviesList.add(credits.cast.get(i));
            }
        }

        correctAnswer = castList.get(randSort).getId();
        System.out.println("FACIT: " +castList.get(randSort).getName());

        questions.theQuestion = "Which movie has "+ credits.cast.get(0).getName() +" not played in?";
        questions.correctAnswer = correctAnswer;
        questions.actorsMoviesList = actorsMoviesList;

        return questions;
    }*/


    public Questions getActorsInMovie(RestTemplate restTemplate) {
        Questions questions = new Questions();
        List<Cast> castList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

        Credits credits = apiService.getRandomMovieCharacters(restTemplate);

        int randSort = rand.nextInt(3);
        for (int i = 0; i < 4; i++) {
            if (randSort == i) {
                Cast wrongAnswer = apiService.getRandomMovieCharacter(restTemplate);
                castList.add(wrongAnswer);
            } else
                castList.add(credits.cast.get(i));
        }

        correctAnswer = castList.get(randSort).getId();
        System.out.println("FACIT: " + castList.get(randSort).getName());

        questions.theQuestion = "Which actor does not have a role in the movie " + credits.cast.get(0).getTitle() + "?";
        questions.correctAnswer = correctAnswer;
        questions.castList = castList;

        return questions;
    }

    public Questions getCharacterQuestion(RestTemplate restTemplate) {
        Questions questions = new Questions();
        List<Cast> castList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

//        for (int i = 0; i < 4; i++) {
//            Cast results = apiService.getRandomMovieCharacter(restTemplate);
//            castList.add(results);
//        }
        while (castList.size()<4){
            Cast results = apiService.getRandomMovieCharacter(restTemplate);
            if (castList.size() == 0){
                castList.add(results);
                continue;
            }

            if (castList.get(0).getGender() == results.getGender()) {
                castList.add(results);
            }
        }




        int randForQandA = rand.nextInt(3);
        // correctAnswer = results.getRelease_date();
        correctAnswer = castList.get(randForQandA).getId();
        System.out.println("FACIT: " + castList.get(randForQandA).getCharacter());

        questions.theQuestion = "What is the name of the character " + castList.get(randForQandA).getName() + " plays in " + castList.get(randForQandA).getTitle() + "?";
        questions.correctAnswer = correctAnswer;
        questions.castList = castList;
        questions.randForQandA = randForQandA;


        return questions;
    }

    public Questions getWhatYearQuestion(RestTemplate restTemplate) {

        List<Results> movieList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();
        boolean isLika = false;
        int firstYear = 0;

        while (movieList.size() < 4) {
            Results results = apiService.getRandomMovie(restTemplate);
            String year = results.release_date.substring(0,4);
            results.release_date = year;
            int intYear = Integer.parseInt(year);

            if (movieList.size() == 0) {
                movieList.add(results);
                firstYear = Integer.parseInt(movieList.get(0).release_date);
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(results.getTitle()) || movieList.get(j).release_date.equals(results.release_date) || Math.abs(intYear - firstYear) > 10) {
                    isLika = true;
                    break;
                } else {
                    isLika = false;
                }
            }
            if (!isLika) {
                movieList.add(results);
            }
        }

        // correctAnswer = results.getRelease_date();
        String correctReleaseDate = movieList.get(randForQandA).getRelease_date();
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " + movieList.get(randForQandA).getRelease_date());
        Questions questions = new Questions(("What date was " + movieList.get(randForQandA).getTitle()) + " released?"
                , movieList
                , correctAnswer);
        questions.randForQandA = randForQandA;
        return questions;
    }

    public Questions getYearForMovieQuestion(RestTemplate restTemplate) {

        List<Results> movieList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();
        boolean isLika = false;

        while (movieList.size() < 4) {
            Results results = apiService.getRandomMovie(restTemplate);
            String year = results.release_date.substring(0,4);
            results.release_date = year;

            if (movieList.size() == 0) {
                movieList.add(results);
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(results.getTitle()) || movieList.get(j).release_date.equals(results.release_date)) {
                    isLika = true;
                    break;
                } else {
                    isLika = false;
                }
            }
            if (!isLika) {
                movieList.add(results);
            }
        }

        int randForQandA = rand.nextInt(3);
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " + movieList.get(randForQandA).getTitle());
        Questions questions = new Questions(("Which movie was released " + movieList.get(randForQandA).getRelease_date()) + "?"
                , movieList
                , correctAnswer);
        return questions;
    }

    //DE TVÅ FRÅGORNA NEDAN ANVÄNDS INTE FÖR TILLFÄLLET

  /*  public Questions getDescQuestion(RestTemplate restTemplate){

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
       Questions questions = new Questions(("Vilken film är det enligt beskrivningen? \n" + movieList.get(randForQandA).getOverview())
               ,movieList
               , correctAnswer);
        return questions;
    }*/

  /*  public Questions getPosterQuestion(RestTemplate restTemplate){
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
    }*/

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

    public int getRandForQandA() {
        return randForQandA;
    }

    public void setRandForQandA(int randForQandA) {
        this.randForQandA = randForQandA;
    }

    public List<ActorsMovies> getActorsMoviesList() {
        return actorsMoviesList;
    }

    public void setActorsMoviesList(List<ActorsMovies> actorsMoviesList) {
        this.actorsMoviesList = actorsMoviesList;
    }
}
