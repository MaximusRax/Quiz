package com.brinux.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.brinux.*;
import com.brinux.quiz.dao.QuestionDao;
import com.brinux.quiz.model.Question;
import com.brinux.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("question")
public class QuestionController {

	private final QuestionDao questionDao;
	@Autowired
	QuestionService questionService;

	QuestionController(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@GetMapping("getAllQuestions")
	public ResponseEntity<List<Question>> getQuestions() {
		try {
			return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
		try {
			return ResponseEntity.ok(questionService.getQuestionByCategory(category));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(questionService.getQuestionByCategory(""));

	}

	@PostMapping("")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		try {
			questionService.addQuestion(question);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
		questionService.deleteQuestion(id);
		return new ResponseEntity<>("Sucess", HttpStatus.OK);
	}
	
}
