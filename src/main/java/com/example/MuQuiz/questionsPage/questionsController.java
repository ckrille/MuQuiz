package com.example.MuQuiz.questionsPage;

        import com.example.MuQuiz.Movie;
        import com.example.MuQuiz.Questions;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.client.RestTemplate;

@Controller
public class questionsController {

   /* @Autowired
    RestTemplate restTemplate;*/
   @Autowired
           Questions questions;


    String APIkey = "21c95e422f1845eea7a7274d9e67524b";
    int movieId;

    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){

        movieId = 100;

        questions.questionDesc(restTemplate);

        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key=" +APIkey +"&language=en-US", Movie.class);
        Movie movie2 = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+(movieId+5) +"?api_key=" +APIkey +"&language=en-US", Movie.class);
        Movie movie3 = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+(movieId+10) +"?api_key=" +APIkey +"&language=en-US", Movie.class);

        model.addAttribute("overview",movie.overview);
        model.addAttribute("url","https://image.tmdb.org/t/p/w200/" +movie.poster_path);
        model.addAttribute("url2","https://image.tmdb.org/t/p/w200/" +movie2.poster_path);
        model.addAttribute("url3","https://image.tmdb.org/t/p/w200/" +movie3.poster_path);



        return "questions";
    }

    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate,Model model, @RequestParam Integer movieId){
        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key=" +APIkey +"&language=en-US", Movie.class);
        Movie movie2 = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+(movieId+5) +"?api_key=" +APIkey +"&language=en-US", Movie.class);
        Movie movie3 = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+(movieId+10) +"?api_key=" +APIkey +"&language=en-US", Movie.class);

        model.addAttribute("overview",movie.overview);
        model.addAttribute("url","https://image.tmdb.org/t/p/w200/" +movie.poster_path);
        model.addAttribute("url2","https://image.tmdb.org/t/p/w200/" +movie2.poster_path);
        model.addAttribute("url3","https://image.tmdb.org/t/p/w200/" +movie3.poster_path);

        return "questions";
    }

}

