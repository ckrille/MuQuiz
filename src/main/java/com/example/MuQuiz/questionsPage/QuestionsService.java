package com.example.MuQuiz.questionsPage;

import com.example.MuQuiz.ApiClasses.*;
import com.example.MuQuiz.ApiClasses.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionsService {

    private String theQuestion;
    private Long correctAnswer;
    private int randForQandA;
    private int typeQuestion;
    private List<Movies> movieList;
    private List<Actor> castList;
    private List<MoviesByActor> actorsMoviesList;

    public QuestionsService() {
    }

    public QuestionsService(String theQuestion, List<Movies> movieList, Long correctAnswer) {
        this.theQuestion = theQuestion;
        this.movieList = movieList;
        this.correctAnswer = correctAnswer;
    }

    public QuestionsService getActorsInMovie(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Actor> castList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

        ActorsApiReceiver actorsApiReceiver = apiService.getRandomMovieCharacters(restTemplate);

        int randSort = rand.nextInt(3);
        for (int i = 0; i < 4; i++) {
            if (randSort == i) {
                Actor wrongAnswer = apiService.getRandomMovieCharacter(restTemplate);
                castList.add(wrongAnswer);
            } else
                castList.add(actorsApiReceiver.getCast().get(i));
        }

        correctAnswer = castList.get(randSort).getId();
        System.out.println("FACIT: " + castList.get(randSort).getName());

        questionsService.theQuestion = "Which actor does not have a role in the movie " + actorsApiReceiver.getCast().get(0).getTitle() + "?";
        questionsService.correctAnswer = correctAnswer;
        questionsService.castList = castList;
        questionsService.typeQuestion = 1;

        return questionsService;
    }

    public QuestionsService getCharacterQuestion(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Actor> castList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

        //fyll ArrayList med fyra Actor-objekt, säkerställ att det är samma kön
        while (castList.size() < 4) {
            Actor actor = apiService.getRandomMovieCharacter(restTemplate);
            if (castList.size() == 0) {
                castList.add(actor);
                continue;
            }
            if (castList.get(0).getGender() == actor.getGender()) {
                castList.add(actor);
            }
        }

        //slumpa fram vilken av de fyra som ska vara den som efterfrågas och därmed också rätt svar
        int randForQandA = rand.nextInt(3);
        correctAnswer = castList.get(randForQandA).getId();
        System.out.println("FACIT: " + castList.get(randForQandA).getCharacter());

        questionsService.theQuestion = "What is the name of the character " + castList.get(randForQandA).getName() + " plays in " + castList.get(randForQandA).getTitle() + "?";
        questionsService.correctAnswer = correctAnswer;
        questionsService.castList = castList;
        questionsService.randForQandA = randForQandA;
        questionsService.typeQuestion = 2;

        return questionsService;
    }

    public QuestionsService getWhatYearQuestion(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Movies> movieList = new ArrayList<>();
        ApiService apiService = new ApiService();
        boolean isSame = false;
        int firstYear = 0;

        while (movieList.size() < 4) {
            Movies movies = apiService.getRandomMovie(restTemplate);
            String year = movies.release_date.substring(0, 4);
            movies.release_date = year;
            int intYear = Integer.parseInt(year);

            if (movieList.size() == 0) {
                movieList.add(movies);
                firstYear = Integer.parseInt(movieList.get(0).release_date);
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(movies.getTitle()) || movieList.get(j).release_date.equals(movies.release_date) || Math.abs(intYear - firstYear) > 10) {
                    isSame = true;
                    break;
                } else {
                    isSame = false;
                }
            }
            if (!isSame) {
                movieList.add(movies);
            }
        }


        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " + movieList.get(randForQandA).getRelease_date());

        questionsService.theQuestion = "What year was " + movieList.get(randForQandA).getTitle() + " released?";
        questionsService.movieList = movieList;
        questionsService.correctAnswer = correctAnswer;
        questionsService.randForQandA = randForQandA;
        questionsService.typeQuestion = 3;
        return questionsService;
    }

    public QuestionsService getYearForMovieQuestion(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Movies> movieList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();
        boolean isSame = false;

        while (movieList.size() < 4) {
            Movies movies = apiService.getRandomMovie(restTemplate);
            String year = movies.release_date.substring(0, 4);
            movies.release_date = year;

            if (movieList.size() == 0) {
                movieList.add(movies);
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(movies.getTitle()) || movieList.get(j).release_date.equals(movies.release_date)) {
                    isSame = true;
                    break;
                } else {
                    isSame = false;
                }
            }
            if (!isSame) {
                movieList.add(movies);
            }
        }

        int randForQandA = rand.nextInt(3);
        correctAnswer = movieList.get(randForQandA).getId();
        System.out.println("FACIT: " + movieList.get(randForQandA).getTitle());

        questionsService.theQuestion = "Which movie was released " + movieList.get(randForQandA).getRelease_date() + "?";
        questionsService.movieList = movieList;
        questionsService.correctAnswer = correctAnswer;
        questionsService.typeQuestion = 4;
        return questionsService;
    }






    //fråga utkommenterad, kanske använda framöver

  /*  public Questions getActorNotInMovie(RestTemplate restTemplate){
        Questions questions = new Questions();
        List<MoviesByActor> actorsMoviesList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        MoviesByActorApiReceiver credits = categoryService.getRandomActorCredits(restTemplate);

        int randSort = rand.nextInt(3);
        for (int i = 0; i < 4; i++) {
            if(randSort == i){
                MoviesByActor wrongAnswer = categoryService.getRandomActorCredit(restTemplate);
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

    //DE TVÅ FRÅGORNA NEDAN ANVÄNDS INTE FÖR TILLFÄLLET

  /*  public Questions getDescQuestion(RestTemplate restTemplate){

        List<Movies> movieList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 4; i++) {
            Movies results = categoryService.getRandomMovie(restTemplate);
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
        Movies results = categoryService.getRandomMovie(restTemplate);
        List<Movies> movieList = new ArrayList<>();

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

    public List<Movies> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movies> movieList) {
        this.movieList = movieList;
    }

    public Long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Actor> getCastList() {
        return castList;
    }

    public void setCastList(List<Actor> castList) {
        this.castList = castList;
    }

    public int getRandForQandA() {
        return randForQandA;
    }

    public void setRandForQandA(int randForQandA) {
        this.randForQandA = randForQandA;
    }

    public List<MoviesByActor> getActorsMoviesList() {
        return actorsMoviesList;
    }

    public void setActorsMoviesList(List<MoviesByActor> actorsMoviesList) {
        this.actorsMoviesList = actorsMoviesList;
    }

    public int getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(int typeQuestion) {
        this.typeQuestion = typeQuestion;
    }
}
