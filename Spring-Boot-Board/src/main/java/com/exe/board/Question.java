package com.exe.board;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //JPA�� ��ƼƼ�� �ν�
public class Question {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	//������ �� question ���� �� �亯�� ����
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
}
