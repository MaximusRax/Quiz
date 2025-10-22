package com.brinux.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brinux.*;
import com.brinux.quiz.model.Question;
import com.brinux.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;

	@GetMapping("getAllQuestions")
	public List<Question> getQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category) {
		return questionService.getQuestionByCategory(category);
	}

	@PostMapping("add")
	public String addQuestion(@RequestBody Question question) {
		questionService.addQuestion(question);
		return "Sucess";

	}

}
