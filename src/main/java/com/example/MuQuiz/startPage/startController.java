package com.example.MuQuiz.startPage;

import com.example.MuQuiz.Movie;
import com.example.MuQuiz.questionsPage.Questions;
import com.example.MuQuiz.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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