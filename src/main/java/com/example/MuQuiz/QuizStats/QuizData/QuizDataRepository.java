package com.example.MuQuiz.QuizStats.QuizData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDataRepository extends CrudRepository<QuizData,Long> {

    public QuizData findTop1ByOrderByCompletedQuizDesc();

    public Iterable<QuizData> findTop10ByOrderByTotalScoreDesc();
}
