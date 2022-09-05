package com.exe.board.answer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.exe.board.question.Question;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	@ManyToOne
	private Question question;
	//답변은 하나의 질문에 여러개가 달릴 수 있다
	//답변은 Many가 되고 질문은 One이 된다
	//Foreing Key가 생성
	
	@ManyToOne
	private SiteUser author;
	//답글수정일
	private LocalDateTime modifyDate;
	
}
