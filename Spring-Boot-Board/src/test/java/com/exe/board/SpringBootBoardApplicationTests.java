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
		q1.setSubject("������ ��Ʈ�� �����ΰ���?");
		q1.setContent("�������� ���� �˰�;��");
		q1.setCreatedDate(LocalDateTime .now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA�� �����ΰ���?");
		q2.setContent("JPA�� ���� �˰�;��");
		q2.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q2);
	}

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
	
	@Test
	void findBySubjectAndContent() {
		//subject�� content�� ��ġ�� �����͸�
		Question q = questionRepository.findBySubjectAndContent("������ ��Ʈ�� �����ΰ���?", 
				"�������� ���� �˰�;��");
		
		assertEquals(1, q.getId());
	}
	
	@Test
	void findBySubjectLike() {
		
		List<Question> lists = questionRepository.findBySubjectLike("������%");
		
		Question q = lists.get(0);
		
		assertEquals("������ ��Ʈ�� �����ΰ���?", q.getSubject());
		
	}

	@Test
	void update() {
		//optional:�����Ͱ� ������ �ڵ����� null�� ��ȯ-error x
		Optional<Question> op = questionRepository.findById(1);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		q.setSubject("������");
		q.setContent("������ ��Ʈ");
		
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
			String subject = String.format("�׽�Ʈ �������Դϴ�:[%03d]", i);
			String content = String.format("������ ��Ʈ�� ���̴�:[%03d]", i);
			
			questionService.create(subject, content ,null);
		}
	}
	
	
}

