package com.example.MuQuiz.QuizStats;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Entity
public class QsData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Integer typeQuestion;
    private Long correctAnswer;
    private Long wrongAnswer1;
    private Long wrongAnswer2;
    private Long wrongAnswer3;
    private Long wrongAnswer4;
    private Integer score;
    private Long userAnswer;


    public QsData(){
    }

    public QsData(Long specifiktQuizID, Integer typeQuestion, Long correctAnswer, Long wrongAnswer1, Long wrongAnswer2, Long wrongAnswer3, Integer score, Long userAnswer) {
        this.questionId = specifiktQuizID;
        this.typeQuestion = typeQuestion;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.score = score;
        this.userAnswer = userAnswer;
    }

    public Long getSpecifiktQuizID() {
        return questionId;
    }

    public void setSpecifiktQuizID(Long specifiktQuizID) {
        this.questionId = specifiktQuizID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Long userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Integer typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public Long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Long getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(Long wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public Long getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(Long wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public Long getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(Long wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getWrongAnswer4() {
        return wrongAnswer4;
    }

    public void setWrongAnswer4(Long wrongAnswer4) {
        this.wrongAnswer4 = wrongAnswer4;
    }
}
