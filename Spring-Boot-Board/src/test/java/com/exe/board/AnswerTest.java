package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exe.board.answer.Answer;
import com.exe.board.answer.AnswerRepository;
import com.exe.board.question.Question;
import com.exe.board.question.QuestionRepository;

@SpringBootTest
public class AnswerTest {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	@Test
	void replySave() {
	
		Optional<Question> op = questionRepository.findById(2);
		
		assertTrue(op.isPresent());
		Question q = op.get();
		
		Answer a = new Answer();
		a.setContent("JPA는 ORM입니다1");
		a.setQuestion(q);
		a.setCreatedDate(LocalDateTime.now());
		
		answerRepository.save(a);
	}
	*/
	@Test
	void replyFind() {
		
		Optional<Answer> op = answerRepository.findById(1);
		assertTrue(op.isPresent());
		
		Answer a = op.get();
		
		assertEquals(2, a.getQuestion().getId());
	}
	
	@Transactional
	@Test
	void replyConnQuestion() {
		
		Optional<Question> op = questionRepository.findById(2);
		assertTrue(op.isPresent());
		Question q = op.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(2, answerList.size());
		assertEquals("JPA는 ORM입니다", answerList.get(0).getContent());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
