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
    private Long answerAltOne;
    private Long answerAltTwo;
    private Long answerAltThree;
    private Long answerAltFour;
    private Integer score;
    private Long userAnswer;


    public QsData(){
    }

    public QsData(Long specifiktQuizID, Integer typeQuestion, Long correctAnswer, Long wrongAnswer1, Long answerAltTwo, Long answerAltThree, Integer score, Long userAnswer) {
        this.questionId = specifiktQuizID;
        this.typeQuestion = typeQuestion;
        this.correctAnswer = correctAnswer;
        this.answerAltOne = wrongAnswer1;
        this.answerAltTwo = answerAltTwo;
        this.answerAltThree = answerAltThree;
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
        return answerAltOne;
    }

    public void setWrongAnswer1(Long wrongAnswer1) {
        this.answerAltOne = wrongAnswer1;
    }

    public Long getAnswerAltTwo() {
        return answerAltTwo;
    }

    public void setAnswerAltTwo(Long answerAltTwo) {
        this.answerAltTwo = answerAltTwo;
    }

    public Long getAnswerAltThree() {
        return answerAltThree;
    }

    public void setAnswerAltThree(Long answerAltThree) {
        this.answerAltThree = answerAltThree;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerAltFour() {
        return answerAltFour;
    }

    public void setAnswerAltFour(Long answerAltFour) {
        this.answerAltFour = answerAltFour;
    }
}
