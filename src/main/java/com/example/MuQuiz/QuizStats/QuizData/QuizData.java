package com.example.MuQuiz.QuizStats.QuizData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class QuizData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long completedQuiz;
    private Integer totalScore;
    private Long quizId;
    private String quizPlayedBy = "";


    public QuizData() {
    }

    public QuizData(Integer totalScore, Long quizId) {
        this.totalScore = totalScore;
        this.quizId = quizId;
    }

    public QuizData(Long completedQuiz, Integer totalScore, Long quizId) {
        this.completedQuiz = completedQuiz;
        this.totalScore = totalScore;
        this.quizId = quizId;
    }

    public QuizData(Integer totalScore, Long quizId, String quizPlayedBy) {
        this.totalScore = totalScore;
        this.quizId = quizId;
        this.quizPlayedBy = quizPlayedBy;
    }

    public Long getCompletedQuiz() {
        return completedQuiz;
    }

    public void setCompletedQuiz(Long completedQuiz) {
        this.completedQuiz = completedQuiz;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuizPlayedBy() {
        return quizPlayedBy;
    }

    public void setQuizPlayedBy(String quizPlayedBy) {
        this.quizPlayedBy = quizPlayedBy;
    }

    public Long getCompletedQuiz() {
        return completedQuiz;
    }
}
