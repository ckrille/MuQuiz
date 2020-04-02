package com.example.MuQuiz.resultsPage;

import com.example.MuQuiz.QuizStats.QuizStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class resultsController {

    @Autowired
    QuizStats score;


    @GetMapping("/results")
    public String showResults(Model model){

        model.addAttribute("score",score.getHighscore());

        return "results";
    }

}
