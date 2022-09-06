package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //JPA가 엔티티로 인식
public class Question {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	//참조한 후 question 삭제 시 답변도 삭제
	//eager 전체데이터가 실행 될 때까지 접근
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	@ManyToOne
	private SiteUser author;
	
	//질문글 수정일
	private LocalDateTime modifyDate;
	
	//추천
	@ManyToMany
	Set<SiteUser> voter;
	
}
