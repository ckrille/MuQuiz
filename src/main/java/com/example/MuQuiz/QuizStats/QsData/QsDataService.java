package com.example.MuQuiz.QuizStats.QsData;

import com.example.MuQuiz.questionsPage.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QsDataService {

    @Autowired
    private QsDataRepository qsDataRepository;


    public QsDataService() {
    }

    public void postQuestionsDataToDB(Questions questions, Long answer, Integer score, Long countQuizId) {
        QsData qsData = new QsData();

        qsData.setCountQuizId(countQuizId);
        qsData.setScore(score);
        qsData.setUserAnswer(answer);
        qsData.setCorrectAnswer(questions.getCorrectAnswer());
        qsData.setTypeQuestion(questions.getTypeQuestion());
        if (questions.getMovieList() == null) {
            System.out.println("size " + questions.getCastList().size());
            qsData.setAnswerAltOne(questions.getCastList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getCastList().get(1).getId());
            qsData.setAnswerAltThree(questions.getCastList().get(2).getId());
            qsData.setAnswerAltFour(questions.getCastList().get(3).getId());
        } else if (questions.getCastList() == null) {
            System.out.println("size " + questions.getMovieList().size());
            qsData.setAnswerAltOne(questions.getMovieList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getMovieList().get(1).getId());
            qsData.setAnswerAltThree(questions.getMovieList().get(2).getId());
            qsData.setAnswerAltFour(questions.getMovieList().get(3).getId());
        }
        qsDataRepository.save(qsData);
    }

    public void postQuestionsDataToDB(Questions questions, Long answer, Long countQuizId) {
        QsData qsData = new QsData();

        qsData.setCountQuizId(countQuizId);
        qsData.setUserAnswer(answer);
        qsData.setCorrectAnswer(questions.getCorrectAnswer());
        qsData.setTypeQuestion(questions.getTypeQuestion());
        if (questions.getMovieList() == null) {
            System.out.println("size " + questions.getCastList().size());
            qsData.setAnswerAltOne(questions.getCastList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getCastList().get(1).getId());
            qsData.setAnswerAltThree(questions.getCastList().get(2).getId());
            qsData.setAnswerAltFour(questions.getCastList().get(3).getId());
        } else if (questions.getCastList() == null) {
            System.out.println("size " + questions.getMovieList().size());
            qsData.setAnswerAltOne(questions.getMovieList().get(0).getId());
            qsData.setAnswerAltTwo(questions.getMovieList().get(1).getId());
            qsData.setAnswerAltThree(questions.getMovieList().get(2).getId());
            qsData.setAnswerAltFour(questions.getMovieList().get(3).getId());
        }
        qsDataRepository.save(qsData);
    }
}
