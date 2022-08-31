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
		q1.setSubject("������ ��Ʈ�� �����ΰ���?");
		q1.setContent("�������� ���� �˰�;��");
		q1.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA�� �����ΰ���?");
		q2.setContent("JPA�� ���� �˰�;��");
		q2.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q2);
	}
	*/
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();
		
		assertEquals(2, lists.size());
		
		Question q = lists.get(0);
		assertEquals("������ ��Ʈ�� �����ΰ���?", q.getSubject());
			
		
	}
	
	@Test
	void findBySubject() {
		
		Question q = questionRepository.findBySubject("������ ��Ʈ�� �����ΰ���?");
		assertEquals(1, q.getId());
		
	}
	
}
