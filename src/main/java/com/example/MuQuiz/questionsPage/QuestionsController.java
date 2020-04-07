package com.example.MuQuiz.questionsPage;


import com.example.MuQuiz.QuizStats.QsData.QsData;
import com.example.MuQuiz.QuizStats.QuizData.QuizDataService;
import com.example.MuQuiz.QuizStats.QsData.QsDataService;
import com.example.MuQuiz.QuizStats.QuizStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuizStats highscore;

    @Autowired
    private QsDataService qsDataService;

    @Autowired
    private QuizDataService quizDataService;


    private Long quizId = 1L;
    private int numOfQuestions;
    public Long a;

    @GetMapping("/questions")
    public String showStart(HttpSession session, RestTemplate restTemplate, Model model) {
        if(session.getAttribute("newPlayer") == null){
            a = quizDataService.getUniqueQuizId();
            quizDataService.saveIt(a);
            numOfQuestions = 0;
            int nyVariabel = 0;
            session.setAttribute("newPlayer", a);
            session.setAttribute("counter", nyVariabel);
            System.out.println("NEWPLAYER FÅR VÄRDET " +a);
        }
        System.out.println("****");
        System.out.println("****");
        System.out.println("Vad är COUNTERN " +session.getAttribute("counter"));

        if ((int)session.getAttribute("counter") == 4) {
            numOfQuestions = 0;
            quizDataService.saveCompletedQuiz((Long)session.getAttribute("newPlayer"));
            System.out.println("SPARA på ID " +(Long)session.getAttribute("newPlayer"));
            quizId++;
            return "redirect:/results";
        }
        System.out.println("Counter: " + numOfQuestions);
        if ((int)session.getAttribute("counter") % 4 == 0) {
            questionsService = questionsService.getYearForMovieQuestion(restTemplate);
            model.addAttribute("url", questionsService.getMovieList());
            model.addAttribute("score", highscore.getHighscore());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("correctId", questionsService.getCorrectAnswer());
            int nyVariabel = (int)session.getAttribute("counter");
            nyVariabel++;
            session.setAttribute("counter",nyVariabel);
            return "questions";
        }
        if ((int)session.getAttribute("counter") % 4 == 1) {
            questionsService = questionsService.getWhatYearQuestion(restTemplate);
            model.addAttribute("url", questionsService.getMovieList().get(questionsService.getRandForQandA()).getPoster_path());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getMovieList());
            model.addAttribute("score", highscore.getHighscore());
            int nyVariabel = (int)session.getAttribute("counter");
            nyVariabel++;
            session.setAttribute("counter",nyVariabel);
            return "questiontype1";
        }
        if ((int)session.getAttribute("counter") % 4 == 2) {
            questionsService = questionsService.getActorsInMovie(restTemplate);
            model.addAttribute("url", questionsService.getActorList());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getActorList());
            model.addAttribute("score", highscore.getHighscore());
            int nyVariabel = (int)session.getAttribute("counter");
            nyVariabel++;
            session.setAttribute("counter",nyVariabel);
            return "questions";
        }

        if ((int)session.getAttribute("counter") % 4 == 3) {
            questionsService = questionsService.getCharacterQuestion(restTemplate);
            model.addAttribute("url", questionsService.getActorList().get(questionsService.getRandForQandA()).getProfile_path());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getActorList());
            model.addAttribute("score", highscore.getHighscore());
            int nyVariabel = (int)session.getAttribute("counter");
            nyVariabel++;
            session.setAttribute("counter",nyVariabel);
            return "questiontype1";
        }
        return "questions";
    }


    @PostMapping("/questions")
    public String postShow(HttpSession session, RestTemplate restTemplate, Model model, @RequestParam Long answer, Integer score) {


        if (questionsService.getCorrectAnswer().equals(answer)) {
            System.out.println("RÄTT SVAR!!!");
            System.out.println(answer);

            Integer currentScore = highscore.getHighscore() + score;
            highscore.setHighscore(currentScore);

            System.out.println("Highscore: " + highscore.getHighscore());
            qsDataService.postQuestionsDataToDB((int)session.getAttribute("counter"), questionsService, answer, score, (Long)session.getAttribute("newPlayer"));
        } else {
            System.err.println("Fel svar!");
            qsDataService.postQuestionsDataToDB((int)session.getAttribute("counter"), questionsService, answer, (Long)session.getAttribute("newPlayer"));
        }


        return "redirect:/questions";
    }

}

