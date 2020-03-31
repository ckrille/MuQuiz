package com.example.MuQuiz.questionsPage;

        import com.example.MuQuiz.Movie;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.client.RestTemplate;

        import java.util.ArrayList;
        import java.util.List;

@Controller
public class questionsController {

   @Autowired
   Questions questions;

int counter  = 0;


    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){
        Questions qu = new Questions();
        List<Movie> movies = new ArrayList<>();

        if(counter % 2 == 0) {
            movies = qu.questionDesc(restTemplate);
        }
        else{
            movies = qu.questionName(restTemplate);
        }
        model.addAttribute("url",movies);
        model.addAttribute("overview",qu.getTheQuestion());
        counter++;
        System.out.println("counter " +counter);
        return "questions";
    }

    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate,Model model,@RequestParam Long answer){
        Questions qu = new Questions();
        List<Movie> movies = new ArrayList<>();
        movies = qu.questionDesc(restTemplate);

        if(qu.getAnswer() == answer) {
            System.out.println(answer);
        }


        return "redirect:/questions";
    }

}

