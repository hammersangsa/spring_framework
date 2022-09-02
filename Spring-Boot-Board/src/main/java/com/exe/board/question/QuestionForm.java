package com.exe.board.question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	@NotEmpty(message = "제목을 입력하세요")
	@Size(max = 200)
	private String subject;
	
	@NotEmpty(message = "내용을 입력하세요")
	private String content;
	/*
	@Size		문자 길이를 제한한다.
	@NotNull	Null을 허용하지 않는다.
	@NotEmpty	Null 또는 빈 문자열("")을 허용하지 않는다.
	@Past		과거 날짜만 가능
	@Future		미래 날짜만 가능
	@FutureOrPresent미래 또는 오늘날짜만 가능
	@Max		최대값
	@Min		최소값
	@Pattern	정규식으로 검증
	*/
}
