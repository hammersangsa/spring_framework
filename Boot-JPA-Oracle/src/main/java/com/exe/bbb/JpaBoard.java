package com.exe.bbb;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="jpaBoard") //사용자 정의
public class JpaBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	
	@Column(nullable = false, length = 30)
	private String title;
	
	@Column(nullable = false, length = 20)
	private String writer;
	
	@Column(length = 200)
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	
	@UpdateTimestamp
	private Timestamp updatedate;
	
}
