package com.example.MuQuiz.QuizStats.QuizData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDataRepository extends CrudRepository<QuizData,Long> {


    public Iterable<QuizData> findAllByOrderByTotalScoreDesc();
}
