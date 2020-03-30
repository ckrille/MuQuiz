package com.example.MuQuiz.startPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class startController {

    @GetMapping("/start")
    public String showStart(){

        return "start";
    }

}
