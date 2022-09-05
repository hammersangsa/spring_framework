package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionRepository;
import com.exe.board.question.QuestionService;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
	}
	/*
	@Test
	void save() {
		
		Question q1 = new Question();
		q1.setSubject("스프링 부트가 무엇인가요?");
		q1.setContent("스프링에 대해 알고싶어요");
		q1.setCreatedDate(LocalDateTime .now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA가 무엇인가요?");
		q2.setContent("JPA에 대해 알고싶어요");
		q2.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q2);
	}

	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();
		
		assertEquals(2, lists.size());
		
		Question q = lists.get(0);
		assertEquals("스프링 부트가 무엇인가요?", q.getSubject());
			
		
	}
	
	@Test
	void findBySubject() {
		
		Question q = questionRepository.findBySubject("스프링 부트가 무엇인가요?");
		assertEquals(1, q.getId());
		
	}
	
	@Test
	void findBySubjectAndContent() {
		//subject와 content가 일치한 데이터만
		Question q = questionRepository.findBySubjectAndContent("스프링 부트가 무엇인가요?", 
				"스프링에 대해 알고싶어요");
		
		assertEquals(1, q.getId());
	}
	
	@Test
	void findBySubjectLike() {
		
		List<Question> lists = questionRepository.findBySubjectLike("스프링%");
		
		Question q = lists.get(0);
		
		assertEquals("스프링 부트가 무엇인가요?", q.getSubject());
		
	}

	@Test
	void update() {
		//optional:데이터가 없으면 자동으로 null값 반환-error x
		Optional<Question> op = questionRepository.findById(1);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		q.setSubject("스프링");
		q.setContent("스프링 부트");
		
		questionRepository.save(q);
		
	}
	
	@Test
	void delete() {
		
		assertEquals(2, questionRepository.count());
		
		Optional<Question> op = questionRepository.findById(1);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		questionRepository.delete(q);
		
		assertEquals(1, questionRepository.count());
	}
	*/
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void save200() {
		
		for(int i=1;i<=200;i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = String.format("스프링 부트는 신이다:[%03d]", i);
			
			questionService.create(subject, content ,null);
		}
	}
	
	
}

