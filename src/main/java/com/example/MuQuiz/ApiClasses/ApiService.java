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

    public Actors getRandomMovieCharacter(RestTemplate restTemplate) {
        boolean randomCharacterNull = true;
        Actors cast = new Actors();
        while(randomCharacterNull) {

        Random rand = new Random();
        int random = rand.nextInt(2);

        Movies result = getRandomMovie(restTemplate);

            ActorsApiReceiver credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsApiReceiver.class);

            if(credits.getCast().size() >= 2) {
                cast = credits.getCast().get(random);
                cast.setTitle(result.getTitle());
                if(cast.getName() != null && cast.getName().length() >= 2 && cast.getCharacter() != null && cast.getCharacter().length() >= 2 && cast.getProfile_path() != null) {
                    randomCharacterNull = false;
                }
            }
        }
        return cast;
    }

    public ActorsApiReceiver getRandomMovieCharacters(RestTemplate restTemplate) {
        boolean randomCharactersNull = true;
        int approvedCharacters = 0;
        ActorsApiReceiver credits = new ActorsApiReceiver();

        while(randomCharactersNull) {
            Movies movies = getRandomMovie(restTemplate);
            credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movies.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsApiReceiver.class);
            for (int i = 0; i < credits.getCast().size(); i++) {
                credits.getCast().get(i).setTitle(movies.getTitle());
            }
            for(int i = 0; i < 4; i++) {
                if (credits.getCast().get(i).getName() != null && credits.getCast().get(i).getName().length() >= 2 && credits.getCast().get(i).getCharacter() != null && credits.getCast().get(i).getCharacter().length() >= 2 && credits.getCast().get(i).getProfile_path() != null) {
                    approvedCharacters++;
                    if(approvedCharacters == 4) {
                        randomCharactersNull = false;
                    }
                }
            }
        }
        return credits;
    }
    //Koden nedan har ingen validering och används inte just nu.
/*
    public MoviesByActor getRandomActorCredit(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(5);

        Actors cast = getRandomMovieCharacter(restTemplate);

        MoviesByActorApiReceiver actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", MoviesByActorApiReceiver.class);
        MoviesByActor actorsMovies = actorsMoviesAPI.cast.get(random);
        actorsMovies.setName(cast.getName());

        return actorsMovies;
    }

    public MoviesByActorApiReceiver getRandomActorCredits(RestTemplate restTemplate) {
        Actors cast = getRandomMovieCharacter(restTemplate);
        MoviesByActorApiReceiver actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", MoviesByActorApiReceiver.class);
        for(int i = 0; i < actorsMoviesAPI.cast.size(); i++) {
            actorsMoviesAPI.cast.get(i).setName(cast.getName());
        }
        return actorsMoviesAPI;
    }

 */

}
