package com.example.MuQuiz.questionsPage;


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

    private int numOfQuestions = 0;
    private Long quizId = 1L;


    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate, Model model) {
        if (numOfQuestions == 4) {
            numOfQuestions = 0;
            quizDataService.saveCompletedQuiz(quizId);
            quizDataService.getCompleteQuiz(quizId);
            quizId++;
            return "redirect:/results";
        }
        System.out.println("Counter: " + numOfQuestions);
        if (numOfQuestions % 4 == 0) {
            questionsService = questionsService.getYearForMovieQuestion(restTemplate);
            model.addAttribute("url", questionsService.getMovieList());
            model.addAttribute("score", highscore.getHighscore());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("correctId", questionsService.getCorrectAnswer());
            numOfQuestions++;
            return "questions";
        }
        if (numOfQuestions % 4 == 1) {
            questionsService = questionsService.getWhatYearQuestion(restTemplate);
            model.addAttribute("url", questionsService.getMovieList().get(questionsService.getRandForQandA()).getPoster_path());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getMovieList());
            model.addAttribute("score", highscore.getHighscore());
            numOfQuestions++;
            return "questiontype1";
        }
        if (numOfQuestions % 4 == 2) {
            questionsService = questionsService.getActorsInMovie(restTemplate);
            model.addAttribute("url", questionsService.getCastList());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getCastList());
            numOfQuestions++;
            return "questions";
        }

        if (numOfQuestions % 4 == 3) {
            questionsService = questionsService.getCharacterQuestion(restTemplate);
            model.addAttribute("url", questionsService.getCastList().get(questionsService.getRandForQandA()).getProfile_path());
            model.addAttribute("overview", questionsService.getTheQuestion());
            model.addAttribute("answer", questionsService.getCastList());
            model.addAttribute("score", highscore.getHighscore());
            numOfQuestions++;
            return "questiontype1";
        }
        return "questions";
    }


    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate, Model model, @RequestParam Long answer, Integer score) {


        if (questionsService.getCorrectAnswer().equals(answer)) {
            System.out.println("RÄTT SVAR!!!");
            System.out.println(answer);

            Integer currentScore = highscore.getHighscore() + score;
            highscore.setHighscore(currentScore);

            System.out.println("Highscore: " + highscore.getHighscore());
            qsDataService.postQuestionsDataToDB(numOfQuestions, questionsService, answer, score, quizId);
        } else {
            System.err.println("Fel svar!");
            qsDataService.postQuestionsDataToDB(numOfQuestions, questionsService, answer, quizId);
        }


        return "redirect:/questions";
    }

}

