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
	@NotEmpty(message = "����� ID�� �ʼ��Դϴ�")
	private String userName;
	
	@NotEmpty(message = "PASSWORD�� �Է��ϼ���")
	private String password1;
	@NotEmpty(message = "PASSWORD�� Ȯ�� ���ּ���")
	private String password2;
	
	@Email
	@NotEmpty(message = "Email�� �ʼ��Դϴ�")
	private String email;
	
}
