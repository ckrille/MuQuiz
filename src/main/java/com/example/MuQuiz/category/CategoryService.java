package com.example.MuQuiz.category;

import com.example.MuQuiz.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class CategoryService {

    public Long getRandomCategory(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(19);

        Genre genre = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Genre.class);

        return genre.genres.get(random).id;
    }

    public Results getRandomMovie(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(20);

        Long id = getRandomCategory(restTemplate);

        ChosenCategory chosenCategory = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=31a12b6ca6c283fb200e5129823f37de&with_genres="+ id + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1", ChosenCategory.class);
        Results results = chosenCategory.results.get(random);

        return results;
    }

    public Cast getRandomMovieCharacter(RestTemplate restTemplate) {
        Random rand = new Random();
        int random = rand.nextInt(2);

        Results result = getRandomMovie(restTemplate);

        Credits credits = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + result.getId() + "/credits?api_key=31a12b6ca6c283fb200e5129823f37de&language=en-US", Credits.class);
        Cast cast = credits.cast.get(random);

        return cast;
    }

}
