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

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ResultsController {

    @Autowired
    QuizStats score;

    @Autowired
    QuizDataService quizDataService;

    @GetMapping("/results")
    public String showResults(HttpSession session,Model model){

        if(session.getAttribute("newPlayer")==null){
            model.addAttribute("highscore",quizDataService.getHighScores());
        }
        else{


        List<QuizData> top10 = quizDataService.getHighScores();
        QuizData newestResultOnSession = quizDataService.getNewestResultOnSession((Long)session.getAttribute("newPlayer"));
        Boolean gotHighscore = false;

        for(int i = 0; i < top10.size(); i++) {
            if(top10.get(i).getCompletedQuiz() == newestResultOnSession.getCompletedQuiz()) {
                gotHighscore = true;
            }
        }

        model.addAttribute("gotHighscore", gotHighscore);

        model.addAttribute("highscore",quizDataService.getHighScores());

        model.addAttribute("score",newestResultOnSession.getTotalScore());

        if(gotHighscore==false){
            session.setAttribute("newPlayer", null);
        }
        }
        return "results";
    }

    @PostMapping("/results")
    public String showResultsWithNewHighscore(HttpSession session, Model model, @RequestParam String quizPlayedBy){

       if(quizPlayedBy.equals("")) {
           quizPlayedBy = "Anonymous";
       }
        QuizData newestResultOnSession = quizDataService.getNewestResultOnSession((Long)session.getAttribute("newPlayer"));

        quizDataService.changeQuizPlayedBy((Long)session.getAttribute("newPlayer"),quizPlayedBy);

        model.addAttribute("entered", true);
        model.addAttribute("highscore",quizDataService.getHighScores());

        model.addAttribute("score",newestResultOnSession.getTotalScore());

        session.setAttribute("newPlayer", null);

        return "results";
    }

   /* @PostMapping("/results")
    public String clearResults(){
        

        return("results");
    }
*/
}
