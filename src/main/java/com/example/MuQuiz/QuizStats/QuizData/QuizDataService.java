package com.example.MuQuiz.QuizStats.QuizData;

import com.example.MuQuiz.QuizStats.QsData.QsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizDataService {

    @Autowired
    private QuizDataRepository quizDataRepository;

    @Autowired
    private QsDataRepository qsDataRepositoryForJPA;

    public QuizDataService() {
    }

    public Integer getTotalScoreOnQuizId(Long quizId) {
        List<Integer> integerList = (List<Integer>) qsDataRepositoryForJPA.getTotalScoreFromQuizId(quizId);
        Integer totalScore = 0;
        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i) != null) {
                totalScore += integerList.get(i);
            }
        }
        return totalScore;
    }

    public void saveCompletedQuiz(Long quizId) {
        Integer totalScore = getTotalScoreOnQuizId(quizId);
        quizDataRepository.save(new QuizData(totalScore, quizId));
    }


}
