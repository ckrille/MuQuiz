package com.example.MuQuiz.QuizStats;

import org.springframework.stereotype.Service;

@Service
public class QuizStats {
    private Integer highscore = 0;

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }
}
