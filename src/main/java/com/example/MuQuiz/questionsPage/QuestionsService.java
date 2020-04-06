package com.example.MuQuiz.questionsPage;

import com.example.MuQuiz.ApiClasses.Actor.Actor;
import com.example.MuQuiz.ApiClasses.Actor.ActorsApiReceiver;
import com.example.MuQuiz.ApiClasses.ApiService;
import com.example.MuQuiz.ApiClasses.Movie.Movie;
import com.example.MuQuiz.ApiClasses.MoviesByActor.MoviesByActor;
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
    private List<Movie> movieList;
    private List<Actor> actorList;
    private List<MoviesByActor> moviesByActorList;

    public QuestionsService() {
    }

    public QuestionsService(String theQuestion, List<Movie> movieList, Long correctAnswer) {
        this.theQuestion = theQuestion;
        this.movieList = movieList;
        this.correctAnswer = correctAnswer;
    }

    public QuestionsService getActorsInMovie(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Actor> actorList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

        ActorsApiReceiver actorsApiReceiver = apiService.getRandomMovieCharacters(restTemplate);

        int randSort = rand.nextInt(3);
        for (int i = 0; i < 4; i++) {
            if (randSort == i) {
                Actor wrongAnswer = apiService.getRandomMovieCharacter(restTemplate);
                actorList.add(wrongAnswer);
            } else
                actorList.add(actorsApiReceiver.getCast().get(i));
        }

        correctAnswer = actorList.get(randSort).getId();
        System.out.println("FACIT: " + actorList.get(randSort).getName());

        questionsService.theQuestion = "Which actor does not have a role in the movie " + actorsApiReceiver.getCast().get(0).getTitle() + "?";
        questionsService.correctAnswer = correctAnswer;
        questionsService.actorList = actorList;
        questionsService.typeQuestion = 1;

        return questionsService;
    }

    public QuestionsService getCharacterQuestion(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Actor> actorList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();

        //fyll ArrayList med fyra Actor-objekt, säkerställ att det är samma kön
        while (actorList.size() < 4) {
            Actor actor = apiService.getRandomMovieCharacter(restTemplate);
            if (actorList.size() == 0) {
                actorList.add(actor);
                continue;
            }
            if (actorList.get(0).getGender() == actor.getGender()) {
                actorList.add(actor);
            }
        }

        //slumpa fram vilken av de fyra som ska vara den som efterfrågas och därmed också rätt svar
        int randForQandA = rand.nextInt(3);
        correctAnswer = actorList.get(randForQandA).getId();
        System.out.println("FACIT: " + actorList.get(randForQandA).getCharacter());

        questionsService.theQuestion = "What is the name of the character " + actorList.get(randForQandA).getName() + " plays in " + actorList.get(randForQandA).getTitle() + "?";
        questionsService.correctAnswer = correctAnswer;
        questionsService.actorList = actorList;
        questionsService.randForQandA = randForQandA;
        questionsService.typeQuestion = 2;

        return questionsService;
    }

    public QuestionsService getWhatYearQuestion(RestTemplate restTemplate) {
        QuestionsService questionsService = new QuestionsService();
        List<Movie> movieList = new ArrayList<>();
        ApiService apiService = new ApiService();
        boolean isSame = false;
        int firstYear = 0;

        while (movieList.size() < 4) {
            Movie movie = apiService.getRandomMovie(restTemplate);
            String year = movie.getRelease_date().substring(0, 4);
            movie.setRelease_date(year);
            int intYear = Integer.parseInt(year);

            if (movieList.size() == 0) {
                movieList.add(movie);
                firstYear = Integer.parseInt(movieList.get(0).getRelease_date());
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(movie.getTitle()) || movieList.get(j).getRelease_date().equals(movie.getRelease_date()) || Math.abs(intYear - firstYear) > 10) {
                    isSame = true;
                    break;
                } else {
                    isSame = false;
                }
            }
            if (!isSame) {
                movieList.add(movie);
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
        List<Movie> movieList = new ArrayList<>();
        Random rand = new Random();
        ApiService apiService = new ApiService();
        boolean isSame = false;

        while (movieList.size() < 4) {
            Movie movie = apiService.getRandomMovie(restTemplate);
            String year = movie.getRelease_date().substring(0, 4);
            movie.setRelease_date(year);

            if (movieList.size() == 0) {
                movieList.add(movie);
            }

            for (int j = 0; j < movieList.size(); j++) {
                if (movieList.get(j).getTitle().equals(movie.getTitle()) || movieList.get(j).getRelease_date().equals(movie.getRelease_date())) {
                    isSame = true;
                    break;
                } else {
                    isSame = false;
                }
            }
            if (!isSame) {
                movieList.add(movie);
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

        List<Movie> movieList = new ArrayList<>();
        Random rand = new Random();
        CategoryService categoryService = new CategoryService();

        for (int i = 0; i < 4; i++) {
            Movie results = categoryService.getRandomMovie(restTemplate);
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
        Movie results = categoryService.getRandomMovie(restTemplate);
        List<Movie> movieList = new ArrayList<>();

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

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public int getRandForQandA() {
        return randForQandA;
    }

    public void setRandForQandA(int randForQandA) {
        this.randForQandA = randForQandA;
    }

    public List<MoviesByActor> getMoviesByActorList() {
        return moviesByActorList;
    }

    public void setMoviesByActorList(List<MoviesByActor> moviesByActorList) {
        this.moviesByActorList = moviesByActorList;
    }

    public int getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(int typeQuestion) {
        this.typeQuestion = typeQuestion;
    }
}
