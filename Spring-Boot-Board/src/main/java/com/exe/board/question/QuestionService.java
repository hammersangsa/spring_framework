package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

//Controller -> Repository에 direct로  DB접근에서
//Controller -> Service -> Repository로 DB간접 접근(Service 매개체)
//Service(method역할)
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	//반환값을 Question으로
	public List<Question> getList(){
		return questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else
			throw new DataNotFoundException("데이터가 없습니다..!");
	}
	
	public void create(String subject, String content) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	
}
