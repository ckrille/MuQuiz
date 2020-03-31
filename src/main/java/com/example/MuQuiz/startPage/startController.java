package com.example.MuQuiz.questionsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class startController {

    @GetMapping("/start")
    public String showQuestions(){

        return "start";
    }

}