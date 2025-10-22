package com.brinux.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brinux.quiz.dao.QuestionDao;
import com.brinux.quiz.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionByCategory(String category) {
		return questionDao.findByCategory(category);
	}

	public Question addQuestion(Question question) {

		return questionDao.save(question);

	}
}
