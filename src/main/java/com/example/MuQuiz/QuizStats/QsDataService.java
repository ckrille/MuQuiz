package com.example.MuQuiz.QuizStats;

import com.example.MuQuiz.questionsPage.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QsDataService {

    @Autowired
    QsDataRepositoryForJPA qsDataRepositoryForJPA;


    public QsDataService() {

    }

    public void setQuestionsWithoutScore(Questions questions, Long answer) {
        QsData qsData = new QsData();

        qsData.setTypeQuestion(3);
        qsData.setUserAnswer(answer);
        qsData.setCorrectAnswer(questions.getCorrectAnswer());
        qsData.setTypeQuestion(questions.getTypeQuestion());
        if(questions.getMovieList()==null){
            System.out.println("size " +questions.getCastList().size());
            qsData.setWrongAnswer1(questions.getCastList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getCastList().get(1).getId());
            qsData.setAnswerAltThree(questions.getCastList().get(2).getId());
            qsData.setAnswerAltFour(questions.getCastList().get(3).getId());

        }
        else if(questions.getCastList()==null){
            System.out.println("size " +questions.getMovieList().size());
            qsData.setWrongAnswer1(questions.getMovieList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getMovieList().get(1).getId());
            qsData.setAnswerAltThree(questions.getMovieList().get(2).getId());
            qsData.setAnswerAltFour(questions.getMovieList().get(3).getId());
        }


        qsDataRepositoryForJPA.save(qsData);
    }

    public void setQuestions(Questions questions, Long answer, Integer score) {
        QsData qsData = new QsData();

        qsData.setTypeQuestion(3);
        qsData.setScore(score);
        qsData.setUserAnswer(answer);
        qsData.setCorrectAnswer(questions.getCorrectAnswer());
        qsData.setTypeQuestion(questions.getTypeQuestion());
        if(questions.getMovieList()==null){
            System.out.println("size " +questions.getCastList().size());
            qsData.setWrongAnswer1(questions.getCastList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getCastList().get(1).getId());
            qsData.setAnswerAltThree(questions.getCastList().get(2).getId());
            qsData.setAnswerAltFour(questions.getCastList().get(3).getId());

        }
        else if(questions.getCastList()==null){
            System.out.println("size " +questions.getMovieList().size());
            qsData.setWrongAnswer1(questions.getMovieList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getMovieList().get(1).getId());
            qsData.setAnswerAltThree(questions.getMovieList().get(2).getId());
            qsData.setAnswerAltFour(questions.getMovieList().get(3).getId());
        }


        qsDataRepositoryForJPA.save(qsData);
    }

}
