package com.example.MuQuiz.QuizStats.QuizData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizDataRepository extends CrudRepository<QuizData,Long> {


    public Iterable<QuizData> findAllByOrderByTotalScoreDesc();

    Iterable<QuizData> findAllByOrderByCompletedQuiz();

    QuizData findByCompletedQuiz(Long uniqueQuizId);
}
