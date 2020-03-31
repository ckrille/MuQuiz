package com.example.MuQuiz.startPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class startController {

    @GetMapping("/")
    public String home() {
        return "/start";
    }

    @PostMapping("/start")
    public String showQuestions(@RequestParam String player){


        return "redirect:/questions";
    }

}