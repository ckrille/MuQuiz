package com.example.MuQuiz.resultsPage;

import com.example.MuQuiz.QuizStats.QuizData.QuizDataService;
import com.example.MuQuiz.QuizStats.QuizStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ResultsController {

    @Autowired
    QuizStats score;

    @Autowired
    QuizDataService quizDataService;

    @GetMapping("/results")
    public String showResults(Model model){

        model.addAttribute("highscore",quizDataService.getHighScores());

        model.addAttribute("score",score.getHighscore());

        return "results";
    }

    @PostMapping("/results")
    public String clearResults(){
        

        return("results");
    }

}
