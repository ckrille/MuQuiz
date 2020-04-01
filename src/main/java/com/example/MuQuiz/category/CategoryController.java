package com.example.MuQuiz.category;
import com.example.MuQuiz.ApiClasses.ActorsMovies;
import com.example.MuQuiz.ApiClasses.ActorsMoviesAPI;
import com.example.MuQuiz.ApiClasses.Cast;

import com.example.MuQuiz.ApiClasses.MovieRefactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    String APIkey = "21c95e422f1845eea7a7274d9e67524b";
    int movieId;

    @GetMapping("/genres")
    public String genre(RestTemplate restTemplate, Model model) {

        MovieRefactor movie = categoryService.getRandomMovie(restTemplate);
        Cast cast = categoryService.getRandomMovieCharacter(restTemplate);
        ActorsMovies actorsMovies = categoryService.getRandomActorCredits(restTemplate);

        model.addAttribute("movie", movie.getTitle());
        model.addAttribute("movieCharacter", cast.getCharacter());
        model.addAttribute("RandomActor", actorsMovies.getName());
        model.addAttribute("actorsMovies", actorsMovies.getTitle());



        return "/genres";
    }

}
