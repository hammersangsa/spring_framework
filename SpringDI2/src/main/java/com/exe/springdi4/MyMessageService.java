package com.exe.springdi4;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageService")
@Scope(value = "prototype")
//이름 미지정 시 클래스명으로 생성
public class MyMessageService implements MessageService {

	public String getMessage() {
		
		return "안녕 오늘은 금요일";
	}

	
	
}
