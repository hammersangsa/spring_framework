package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		q1.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA가 무엇인가요?");
		q2.setContent("JPA에 대해 알고싶어요");
		q2.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q2);
	}
	*/
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
	
}
