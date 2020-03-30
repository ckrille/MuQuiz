package com.example.MuQuiz.startPage;

import com.example.MuQuiz.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class startController {
    String APIkey = "21c95e422f1845eea7a7274d9e67524b";
    int movieId;

    @GetMapping("/start")
    public String showStart(RestTemplate restTemplate, Model model){

        movieId = 640;

        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key=" +APIkey +"&language=en-US", Movie.class);

        model.addAttribute("overview",movie.overview);
        model.addAttribute("url","https://image.tmdb.org/t/p/w200/" +movie.poster_path);


        return "start";
    }

    @PostMapping("/start")
    public String postShow(Model model, RestTemplate restTemplate, @RequestParam Integer movieId){
        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key=" +APIkey +"&language=en-US", Movie.class);

        model.addAttribute("overview",movie.overview);
        model.addAttribute("url","https://image.tmdb.org/t/p/w200/" +movie.poster_path);

        return "start";
    }

}
