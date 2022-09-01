package com.exe.board.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.exe.board.question.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	public void create(Question question,String content) {
		
		Answer ans = new Answer();
		
		ans.setContent(content);
		ans.setCreatedDate(LocalDateTime.now());
		ans.setQuestion(question);
		
		answerRepository.save(ans);
	}
}
