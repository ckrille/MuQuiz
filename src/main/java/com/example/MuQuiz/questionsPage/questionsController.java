package com.example.MuQuiz.questionsPage;

        import com.example.MuQuiz.User.User;
        import com.example.MuQuiz.User.UserRepository;
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
   Questions questions;




    @GetMapping("/questions")
    public String showStart(RestTemplate restTemplate,Model model){
        Questions qu = new Questions();

        model.addAttribute("url",qu.questionDesc(restTemplate));
        model.addAttribute("overview",qu.getTheQuestion());

        return "questions";
    }

    @PostMapping("/questions")
    public String postShow(RestTemplate restTemplate, Model model){
        Questions qu = new Questions();
        User u = new User();

        model.addAttribute("username",u.getUsername());
        model.addAttribute("url",qu.questionName(restTemplate));
        model.addAttribute("overview",qu.getTheQuestion());
        return "questions";
    }

}

