package com.exe.board.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.exe.board.question.Question;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	public Answer create(Question question,String content, SiteUser author) {
		
		Answer ans = new Answer();
		
		ans.setContent(content);
		ans.setCreatedDate(LocalDateTime.now());
		ans.setQuestion(question);
		ans.setAuthor(author);
		
		answerRepository.save(ans);
		
		return ans;
	}
	//답변조회
	public Answer getAnswer(Integer id) {
		
		Optional<Answer> answer = answerRepository.findById(id);
		
		if(answer.isPresent()) {
			
			return answer.get();
			
		}else {
			throw new DataNotFoundException("답글이 없습니다.");
		}
	}
	//답변수정
	public void modify(Answer answer, String content) {
		
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		
		answerRepository.save(answer);
	}
	//답변삭제
	public void delete(Answer answer) {
		
		answerRepository.delete(answer);
	}
	//답변추천
	public void vote(Answer answer, SiteUser siteUser) {
		
		answer.getVoter().add(siteUser);
		
		answerRepository.save(answer);
		
	}
	
	
}
