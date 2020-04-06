package com.example.MuQuiz.QuizStats.QuizData;

import com.example.MuQuiz.QuizStats.QsData.QsData;
import com.example.MuQuiz.QuizStats.QsData.QsDataRepository;
import com.example.MuQuiz.questionsPage.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizDataService {

    @Autowired
    private QuizDataRepository quizDataRepository;

    @Autowired
    private QsDataRepository qsDataRepository;

    public QuizDataService() {
    }

    public QuizData getNewestResult(){

        QuizData quizData = quizDataRepository.findTop1ByOrderByCompletedQuizDesc();

        return quizData;
    }

    public List <QuizData> getHighScores(){

        List<QuizData> quizDataList = (List<QuizData>) quizDataRepository.findTop10ByOrderByTotalScoreDesc();


        return quizDataList;
    }

    public QuestionsService getCompleteQuiz(Long quizId){
        QuestionsService questionsService = new QuestionsService();

        List<QsData> longList = (List<QsData>)qsDataRepository.findByQuizId(quizId);




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

    public void saveIt(Long a){
        QuizData quizData = new QuizData();
        quizDataRepository.save(quizData);
    }

    public void saveCompletedQuiz(Long quizId) {
        Integer totalScore = getTotalScoreOnQuizId(quizId);
        Long uniqueQuizId = getUniqueQuizId();

        QuizData quizData = quizDataRepository.findByCompletedQuiz(quizId);
        quizData.setTotalScore(totalScore);
        quizData.setQuizId(quizId);

        quizDataRepository.save(quizData);
    }

    public void changeQuizPlayedBy(String quizPlayedBy) {
        QuizData quizData = quizDataRepository.findTop1ByOrderByCompletedQuizDesc();
        quizData.setQuizPlayedBy(quizPlayedBy);
        quizDataRepository.save(quizData);
    }


    public Long getUniqueQuizId() {
        QuizData quizData = new QuizData();

        List<QuizData> quizDataList = (List<QuizData>) quizDataRepository.findAllByOrderByCompletedQuiz();
        System.out.println("vad " +quizDataList.size());

        //quizDataRepository.save(quizData);

        return quizDataList.size() == 0 ? 1L  : quizDataList.size() ;

    }
}
