package com.brinux.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brinux.quiz.model.QuestionWrapper;
import com.brinux.quiz.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
