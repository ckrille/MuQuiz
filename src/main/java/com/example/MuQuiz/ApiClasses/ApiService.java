package com.example.MuQuiz.ApiClasses;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class ApiService {

    public Long getRandomCategory(RestTemplate restTemplate) {
        Long categoryId = 0L;
        boolean categoryCheck = true;

        while (categoryCheck) {
            Random rand = new Random();
            int random = rand.nextInt(19);

            CategoryApiReceiver categoryApiReceiver = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", CategoryApiReceiver.class);
            if(categoryApiReceiver.getGenres().get(random).getId() != 99L && categoryApiReceiver.getGenres().get(random).getId() != 10751L && categoryApiReceiver.getGenres().get(random).getId() != 10402L && categoryApiReceiver.getGenres().get(random).getId() != 10770L) {
                categoryId = categoryApiReceiver.getGenres().get(random).getId();
                categoryCheck = false;
            }
        }
        return categoryId;
    }

    public Movies getRandomMovie(RestTemplate restTemplate) {
        boolean randomMovieNull = true;
        Movies movies = new Movies();

        Long id = getRandomCategory(restTemplate);

        while(randomMovieNull) {
            Random rand = new Random();
            int random = rand.nextInt(20);
            int page = rand.nextInt(  4)+1;

            MoviesApiReceiver moviesApiReceiver = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=31a12b6ca6c283fb200e5129823f37de&with_genres=" + id + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + page, MoviesApiReceiver.class);
            movies = moviesApiReceiver.getResults().get(random);
            if (movies.getRelease_date() != null && movies.getRelease_date().length() >= 4 && movies.getPoster_path() != null) {
                randomMovieNull = false;
            }

        }
        return movies;
    }

    public Actor getRandomMovieCharacter(RestTemplate restTemplate) {
        boolean randomCharacterNull = true;
        Actor actor = new Actor();
        while(randomCharacterNull) {

        Random rand = new Random();
        int random = rand.nextInt(2);

        Movies result = getRandomMovie(restTemplate);

            ActorsApiReceiver actorsApiReceiver = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsApiReceiver.class);

            if(actorsApiReceiver.getCast().size() >= 2) {
                actor = actorsApiReceiver.getCast().get(random);
                actor.setTitle(result.getTitle());
                if(actor.getName() != null && actor.getName().length() >= 2 && actor.getCharacter() != null && actor.getCharacter().length() >= 2 && actor.getProfile_path() != null) {
                    randomCharacterNull = false;
                }
            }
        }
        return actor;
    }

    public ActorsApiReceiver getRandomMovieCharacters(RestTemplate restTemplate) {
        boolean randomCharactersNull = true;
        int approvedCharacters = 0;
        ActorsApiReceiver actorsApiReceiver = new ActorsApiReceiver();

        while(randomCharactersNull) {
            Movies movies = getRandomMovie(restTemplate);
            actorsApiReceiver = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movies.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsApiReceiver.class);
            for (int i = 0; i < actorsApiReceiver.getCast().size(); i++) {
                actorsApiReceiver.getCast().get(i).setTitle(movies.getTitle());
            }
            for(int i = 0; i < 4; i++) {
                if (actorsApiReceiver.getCast().get(i).getName() != null && actorsApiReceiver.getCast().get(i).getName().length() >= 2 && actorsApiReceiver.getCast().get(i).getCharacter() != null && actorsApiReceiver.getCast().get(i).getCharacter().length() >= 2 && actorsApiReceiver.getCast().get(i).getProfile_path() != null) {
                    approvedCharacters++;
                    if(approvedCharacters == 4) {
                        randomCharactersNull = false;
                    }
                }
            }
        }
        return actorsApiReceiver;
    }
    //Koden nedan har ingen validering och används inte just nu.
/*
    public MoviesByActor getRandomActorCredit(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(5);

        Actor cast = getRandomMovieCharacter(restTemplate);

        MoviesByActorApiReceiver actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", MoviesByActorApiReceiver.class);
        MoviesByActor actorsMovies = actorsMoviesAPI.cast.get(random);
        actorsMovies.setName(cast.getName());

        return actorsMovies;
    }

    public MoviesByActorApiReceiver getRandomActorCredits(RestTemplate restTemplate) {
        Actor cast = getRandomMovieCharacter(restTemplate);
        MoviesByActorApiReceiver actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", MoviesByActorApiReceiver.class);
        for(int i = 0; i < actorsMoviesAPI.cast.size(); i++) {
            actorsMoviesAPI.cast.get(i).setName(cast.getName());
        }
        return actorsMoviesAPI;
    }

 */

}
