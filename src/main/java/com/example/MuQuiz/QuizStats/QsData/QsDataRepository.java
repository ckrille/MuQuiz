package com.example.MuQuiz.QuizStats.QsData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QsDataRepository extends CrudRepository<QsData, Long> {


    @Query("SELECT score FROM QsData u WHERE u.quizId = ?1")
    Iterable<Integer> getTotalScoreFromQuizId(Long countQuizId);

    public Iterable<QsData> findByQuizId(Long quizId);
}
