package com.brinux.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brinux.quiz.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	public List<Question> findByCategory(String category);

	@Query(value = "select * from question q where q.category=:category order by Rand() limit :numQ", nativeQuery = true)
	public List<Question> findRandomQuestionByCategory(@Param("category") String category,@Param("numQ") int numQ);
}
