package com.example.MuQuiz.questionsPage;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.client.RestTemplate;

@Controller
public class questionsController {

    @Autowired
    Questions qu;

int counter  = 0;


    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){

        if(counter % 2 == 0) {
            qu = qu.getDescQuestion(restTemplate);
        }
        else{
            qu = qu.getPosterQuestion(restTemplate);
        }
        model.addAttribute("url",qu.getMovieList());
        model.addAttribute("overview",qu.getTheQuestion());
        counter++;
        return "questions";
    }

    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate,Model model,@RequestParam Long answer){

        if(qu.getCorrectAnswer().equals(answer)) {
            System.out.println("RÄTT SVAR!!!");
            System.out.println(answer);
        }
        else{
            System.err.println("Fel svar!");
        }
        return "redirect:/questions";
    }

}

