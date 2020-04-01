package com.example.MuQuiz.startPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class startController {

    @GetMapping("/")
    public String home() {
        return "/start";
    }

    @PostMapping("/start")
    public String showQuestions(){


        return "redirect:/questions";
    }

}