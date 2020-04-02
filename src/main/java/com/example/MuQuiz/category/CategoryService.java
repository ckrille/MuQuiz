package com.example.MuQuiz.category;

import com.example.MuQuiz.ApiClasses.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class CategoryService {

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
        Random rand = new Random();
        int random = rand.nextInt(3)+1;

        Long id = getRandomCategory(restTemplate);

        ChosenCategory chosenCategory = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=31a12b6ca6c283fb200e5129823f37de&with_genres="+ id + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page="+random, ChosenCategory.class);
        Results results = chosenCategory.results.get(random);

        return results;
    }

    public Cast getRandomMovieCharacter(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(2);

        Results result = getRandomMovie(restTemplate);

        Credits credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Credits.class);
        Cast cast = credits.cast.get(random);
        cast.setTitle(result.getTitle());

        return cast;
    }

    public ActorsMovies getRandomActorCredits(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(5);

        Cast cast = getRandomMovieCharacter(restTemplate);

        ActorsMoviesAPI actorsMoviesAPI = restTemplate.getForObject("https://api.themoviedb.org/3/person/" + cast.getId() + "/movie_credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", ActorsMoviesAPI.class);
        ActorsMovies actorsMovies = actorsMoviesAPI.cast.get(random);
        actorsMovies.setName(cast.getName());

        return actorsMovies;
    }

}
