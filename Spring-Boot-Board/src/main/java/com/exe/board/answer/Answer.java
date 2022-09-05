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
	//�亯�� �ϳ��� ������ �������� �޸� �� �ִ�
	//�亯�� Many�� �ǰ� ������ One�� �ȴ�
	//Foreing Key�� ����
	
	@ManyToOne
	private SiteUser author;
	//��ۼ�����
	private LocalDateTime modifyDate;
	
}
