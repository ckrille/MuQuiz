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
public class questionsController {

    @Autowired
    private Questions questions;

    @Autowired
    private QuizStats highscore;

    @Autowired
    private QsDataService qsDataService;

    @Autowired
    private QuizDataService quizDataService;

    private int numOfQuestions = 0;
    private Long quizId = 1L;


    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){
        if(numOfQuestions == 3){
            numOfQuestions = 0;
            quizDataService.saveCompletedQuiz(quizId);
            quizId++;
            return "redirect:/results";
        }
        System.out.println("Counter: "+ numOfQuestions);
        if(numOfQuestions % 4 == 0) {
            questions = questions.getYearForMovieQuestion(restTemplate);
            model.addAttribute("url", questions.getMovieList());

        }
        if(numOfQuestions % 4 == 1){
           /* qu = qu.getCharacterQuestion(restTemplate);*/
            questions = questions.getWhatYearQuestion(restTemplate);

            model.addAttribute("url", questions.getMovieList().get(questions.getRandForQandA()).poster_path);
            model.addAttribute("overview", questions.getTheQuestion());
            model.addAttribute("answer", questions.getMovieList());
            model.addAttribute("score",highscore.getHighscore());
            numOfQuestions++;
            return "questiontype1";
            //qu = qu.getPosterQuestion(restTemplate);
        }
        if (numOfQuestions % 4 == 2) {
            questions = questions.getActorsInMovie(restTemplate);
            numOfQuestions++;
            model.addAttribute("url", questions.getCastList());
            model.addAttribute("overview", questions.getTheQuestion());
            model.addAttribute("answer", questions.getCastList());

            return "questions";
        }

        if (numOfQuestions % 4 == 3) {
            questions = questions.getCharacterQuestion(restTemplate);
            numOfQuestions++;
            model.addAttribute("url", questions.getCastList().get(questions.getRandForQandA()).profile_path);
            model.addAttribute("overview", questions.getTheQuestion());
            model.addAttribute("answer", questions.getCastList());
            model.addAttribute("score",highscore.getHighscore());
            return "questiontype1";
        }
        model.addAttribute("score",highscore.getHighscore());
        model.addAttribute("overview", questions.getTheQuestion());
        model.addAttribute("correctId", questions.getCorrectAnswer());
        numOfQuestions++;

        return "questions";
    }


    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate,Model model,@RequestParam Long answer, Integer score){



        if(questions.getCorrectAnswer().equals(answer)) {
            System.out.println("RÄTT SVAR!!!");
            System.out.println(answer);

            Integer currentScore = highscore.getHighscore()+score;
            highscore.setHighscore(currentScore);

            System.out.println("Highscore: "+ highscore.getHighscore());
            qsDataService.postQuestionsDataToDB(numOfQuestions,questions, answer, score, quizId);
        }
        else{
            System.err.println("Fel svar!");
            qsDataService.postQuestionsDataToDB(numOfQuestions,questions, answer, quizId);
        }


        return "redirect:/questions";
    }

}

