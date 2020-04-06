package com.example.MuQuiz.resultsPage;

import com.example.MuQuiz.QuizStats.QuizData.QuizData;
import com.example.MuQuiz.QuizStats.QuizData.QuizDataService;
import com.example.MuQuiz.QuizStats.QuizStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ResultsController {

    @Autowired
    QuizStats score;

    @Autowired
    QuizDataService quizDataService;

    @GetMapping("/results")
    public String showResults(Model model){

        List<QuizData> top10 = quizDataService.getHighScores();
        QuizData newestResult = quizDataService.getNewestResult();
        Boolean gotHighscore = true;

        for(int i = 0; i < top10.size(); i++) {
            if(top10.get(i).getCompletedQuiz() == newestResult.getCompletedQuiz()) {
                gotHighscore = false;
            }
        }

        model.addAttribute("entered", gotHighscore);

        model.addAttribute("highscore",quizDataService.getHighScores());

        model.addAttribute("score",score.getHighscore());

        return "results";
    }

    @PostMapping("/results")
    public String showResultsWithNewHighscore(Model model, @RequestParam String quizPlayedBy){

        quizDataService.changeQuizPlayedBy(quizPlayedBy);

        model.addAttribute("entered", true);
        model.addAttribute("highscore",quizDataService.getHighScores());

        model.addAttribute("score",score.getHighscore());

        return "results";
    }

   /* @PostMapping("/results")
    public String clearResults(){
        

        return("results");
    }
*/
}
