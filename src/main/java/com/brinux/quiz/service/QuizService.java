package com.brinux.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brinux.quiz.QuizApplication;
import com.brinux.quiz.dao.QuestionDao;
import com.brinux.quiz.dao.QuizDao;
import com.brinux.quiz.model.Question;
import com.brinux.quiz.model.QuestionWraper;
import com.brinux.quiz.model.Quiz;
import com.brinux.quiz.model.Response;

@Service
public class QuizService {

	private final QuizApplication quizApplication;

	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;

	QuizService(QuizApplication quizApplication) {
		this.quizApplication = quizApplication;
	}

	public Quiz createQuiz(String category, int numQ, String title) {
		final List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

		Quiz quiz = null;
		try {
			quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setCategory(category);
			quiz.setQuestions(questions);
			quizDao.save(quiz);
			return quiz;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return quiz;
	}

	public List<Quiz> getAllQuiz() {

		return quizDao.findAll();
	}

	public Optional<Quiz> getQuizForExam(int id) {
		return quizDao.findById(id);
	}

	public ResponseEntity<List<QuestionWraper>> getQuiz(int id) {
		Optional<Quiz> quiz = quizDao.findById(id);

		List<Question> questionFormDB = quiz.get().getQuestions();
		List<QuestionWraper> questionForStud = new ArrayList<QuestionWraper>();
		for (Question q : questionFormDB) {
			QuestionWraper qw = new QuestionWraper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());
			questionForStud.add(qw);
		}

		return ResponseEntity.ok(questionForStud);

	}

	public int getScore(int id, List<Response> res) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> question = quiz.getQuestions();
		int correct = 0;
		int i = 0;

		for (Response r : res) {
			if (r.getResponse().equals(question.get(i).getRightAnswer())) {
				correct++;
			}
		}

		return correct;
	}
}
