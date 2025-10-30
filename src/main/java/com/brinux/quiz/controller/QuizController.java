package com.brinux.quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brinux.quiz.model.QuestionWraper;
import com.brinux.quiz.model.Quiz;
import com.brinux.quiz.model.Response;
import com.brinux.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizService;

	@GetMapping("/getAllQuiz")
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		return ResponseEntity.ok(quizService.getAllQuiz());

	}

	@GetMapping("create")
	public ResponseEntity<Optional<Quiz>> createQuiz(@RequestParam String category, @RequestParam Integer numQ,
			@RequestParam String title) {
		Optional<Quiz> quiz = java.util.Optional.empty();
		try {
			quiz = Optional.of(quizService.createQuiz(category, numQ, title));
			if (quiz == null)
				throw new Exception("Error");
			return new ResponseEntity<>(quiz, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(quiz, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWraper>> getQuiz(@PathVariable int id) {
		return quizService.getQuiz(id);
	}

	@PostMapping("getScore/{id}")
	public int getScore(@PathVariable int id, @RequestBody List<Response> res) {
		return quizService.getScore(id, res);
	}
}
