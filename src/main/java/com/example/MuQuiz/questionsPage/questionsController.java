package com.example.MuQuiz.questionsPage;

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
    Questions qu;

int counter  = 0;


    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){

        if(counter % 4 == 0) {
            qu = qu.getYearForMovieQuestion(restTemplate);
            model.addAttribute("url",qu.getMovieList());

        }
        if(counter % 4 == 1){
           /* qu = qu.getCharacterQuestion(restTemplate);*/
            qu = qu.getWhatYearQuestion(restTemplate);

            model.addAttribute("url",qu.getMovieList().get(qu.getRandForQandA()).poster_path);
            model.addAttribute("overview",qu.getTheQuestion());
            model.addAttribute("answer", qu.getMovieList());

            counter++;
            return "questiontype1";
            //qu = qu.getPosterQuestion(restTemplate);
        }
        if (counter % 4 == 2) {
            qu = qu.getActorNotInMovie(restTemplate);
            counter++;
            model.addAttribute("url", qu.getCastList());
            model.addAttribute("overview",qu.getTheQuestion());
            model.addAttribute("answer", qu.getCastList());

            return "questions";
        }

        if (counter % 4 == 3) {
            qu = qu.getCharacterQuestion(restTemplate);
            counter++;
            model.addAttribute("url", qu.getCastList().get(qu.getRandForQandA()).profile_path);
            model.addAttribute("overview",qu.getTheQuestion());
            model.addAttribute("answer", qu.getCastList());

            return "questiontype1";
        }

        model.addAttribute("overview",qu.getTheQuestion());
        model.addAttribute("correctId", qu.getCorrectAnswer());
        counter++;
        return "questions";
    }

    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate,Model model,@RequestParam Long answer){

        if(qu.getCorrectAnswer().equals(answer)) {
            System.out.println("RÄTT SVAR!!!");
            System.out.println(answer);
        }
        else{
            System.err.println("Fel svar!");
        }
        return "redirect:/questions";
    }

}

