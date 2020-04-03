package com.example.MuQuiz.QuizStats;

import com.example.MuQuiz.questionsPage.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QsDataService {

    @Autowired
    QsDataRepositoryForJPA qsDataRepositoryForJPA;



    public QsDataService(){

    }

    public void setQuestions(Questions questions, Long answer, Integer score) {
    QsData qsData = new QsData();

        qsData.setTypeQuestion(3);
        qsData.setCorrectAnswer(questions.getCorrectAnswer());
        qsData.setWrongAnswer1(questions.getCorrectAnswer());
        qsData.setWrongAnswer2(questions.getCorrectAnswer());
        qsData.setWrongAnswer3(questions.getCorrectAnswer());
        qsData.setScore(score);
        qsData.setUserAnswer(answer);

       qsDataRepositoryForJPA.save(qsData);
}


}
