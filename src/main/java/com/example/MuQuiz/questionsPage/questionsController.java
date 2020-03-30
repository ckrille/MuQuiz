package com.example.MuQuiz.questionsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class questionsController {

    @GetMapping("/questions")
    public String showQuestions(){

        return "questions";
    }

}
