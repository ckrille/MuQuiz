package com.example.MuQuiz.QuizStats;

import com.example.MuQuiz.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QsDataRepositoryForJPA extends CrudRepository<QsData,Long> {


}
