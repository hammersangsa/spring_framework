package com.exe.springdi4;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageService")
@Scope(value = "prototype")
//�̸� ������ �� Ŭ���������� ����
public class MyMessageService implements MessageService {

	public String getMessage() {
		
		return "�ȳ� ������ �ݿ���";
	}

	
	
}