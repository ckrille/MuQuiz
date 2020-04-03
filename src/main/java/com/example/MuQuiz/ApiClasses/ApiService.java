package com.example.MuQuiz.ApiClasses;

import com.example.MuQuiz.ApiClasses.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class ApiService {

    public Long getRandomCategory(RestTemplate restTemplate) {
        Long category = 0L;
        boolean categoryCheck = true;

        while (categoryCheck) {
            Random rand = new Random();
            int random = rand.nextInt(19);

            Genre genre = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Genre.class);
            if(genre.genres.get(random).id != 99L && genre.genres.get(random).id != 10751L && genre.genres.get(random).id != 10402L && genre.genres.get(random).id != 10770L) {
                category = genre.genres.get(random).id;
                categoryCheck = false;
            }
        }
        return category;
    }

    public Results getRandomMovie(RestTemplate restTemplate) {
        boolean randomMovieNull = true;
        Results results = new Results();

        Long id = getRandomCategory(restTemplate);

        while(randomMovieNull) {
            Random rand = new Random();
            int random = rand.nextInt(20);
            int page = rand.nextInt(  4)+1;

            ChosenCategory chosenCategory = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=31a12b6ca6c283fb200e5129823f37de&with_genres=" + id + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + page, ChosenCategory.class);
            results = chosenCategory.results.get(random);
            if (results.getRelease_date() != null && results.getPoster_path() != null) {
                randomMovieNull = false;
            }

        }
        return results;
    }

    public Cast getRandomMovieCharacter(RestTemplate restTemplate) {
        boolean randomCharacterNull = true;
        Cast cast = new Cast();
        while(randomCharacterNull) {

        Random rand = new Random();
        int random = rand.nextInt(2);

        Results result = getRandomMovie(restTemplate);

            Credits credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Credits.class);
            cast = credits.cast.get(random);
            cast.setTitle(result.getTitle());
            if(cast.getName() != null && cast.getCharacter() != null && cast.getProfile_path() != null) {
                randomCharacterNull = false;
            }
        }
        return cast;
    }

    public Credits getRandomMovieCharacters(RestTemplate restTemplate) {
        boolean randomCharactersNull = true;
        Credits credits = new Credits();

        while(randomCharactersNull) {
            Results result = getRandomMovie(restTemplate);
            credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Credits.class);
            for (int i = 0; i < credits.cast.size(); i++) {
                credits.cast.get(i).setTitle(result.getTitle());
            }
            for(int i = 0; i < 4; i++) {
                if (credits.cast.get(i).getName() != null && credits.cast.get(i).getCharacter() != null && credits.cast.get(i).getProfile_path() != null) {
                    randomCharactersNull = false;
                }
                else {
                    randomCharactersNull = true;
                }
            }
        }
        return credits;
    }

    public ActorsMovies getRandomActorCredit(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(5);

        Cast cast = getRandomMovieCharacter(restTemplate);

        ActorsMoviesAPI actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsMoviesAPI.class);
        ActorsMovies actorsMovies = actorsMoviesAPI.cast.get(random);
        actorsMovies.setName(cast.getName());

        return actorsMovies;
    }

    public ActorsMoviesAPI getRandomActorCredits(RestTemplate restTemplate) {
        Cast cast = getRandomMovieCharacter(restTemplate);
        ActorsMoviesAPI actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsMoviesAPI.class);
        for(int i = 0; i < actorsMoviesAPI.cast.size(); i++) {
            actorsMoviesAPI.cast.get(i).setName(cast.getName());
        }
        return actorsMoviesAPI;
    }

}
