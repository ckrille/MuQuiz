package com.example.MuQuiz.resultsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class resultsController {

    @GetMapping("/results")
    public String showResults(){

        return "results";
    }

}
