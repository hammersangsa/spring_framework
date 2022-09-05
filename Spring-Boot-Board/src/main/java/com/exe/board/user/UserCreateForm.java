package com.exe.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3,max = 25)
	@NotEmpty(message = "사용자 ID는 필수입니다")
	private String userName;
	
	@NotEmpty(message = "PASSWORD는 입력하세요")
	private String password1;
	@NotEmpty(message = "PASSWORD를 확인 해주세요")
	private String password2;
	
	@Email
	@NotEmpty(message = "Email은 필수입니다")
	private String email;
	
}
