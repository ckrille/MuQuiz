package com.example.MuQuiz.QuizStats.QuizData;

import com.example.MuQuiz.QuizStats.QsData.QsData;
import com.example.MuQuiz.QuizStats.QsData.QsDataRepository;
import com.example.MuQuiz.questionsPage.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizDataService {

    @Autowired
    private QuizDataRepository quizDataRepository;

    @Autowired
    private QsDataRepository qsDataRepository;

    public QuizDataService() {
    }

    public List <QuizData> getHighScores(){

        List<QuizData> quizDataList = (List<QuizData>) quizDataRepository.findAllByOrderByTotalScoreDesc();

        return quizDataList;
    }

    public QuestionsService getCompleteQuiz(Long quizId){
        QuestionsService questionsService = new QuestionsService();

        List<QsData> longList = (List<QsData>)qsDataRepository.findByQuizId(quizId);
        System.out.println("*****");
        System.out.println(longList.size());
        System.out.println(longList.get(0).getAnswerAltOne());
        System.out.println(longList.get(0).getAnswerAltTwo());
        System.out.println(longList.get(0).getAnswerAltThree());
        System.out.println(longList.get(0).getAnswerAltFour());
        System.out.println(longList.get(0).getQuizId());
        System.out.println("*****");



        return questionsService;
    }

    public Integer getTotalScoreOnQuizId(Long quizId) {
        List<Integer> integerList = (List<Integer>) qsDataRepository.getTotalScoreFromQuizId(quizId);
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

    public void changeQuizPlayedBy(String quizPlayedBy) {
        List<QuizData> quizData = (List<QuizData>) quizDataRepository.findFirstByOrderByCompletedQuizDesc();
        quizData.get(0).setQuizPlayedBy(quizPlayedBy);
        quizDataRepository.save(quizData.get(0));
    }


}
