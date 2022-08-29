package com.exe.springdi3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageService {

	public void messageService() {
		
		//BeanFactory »ý¼º
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		//Bean È¹µæ
		Message ms = (Message)context.getBean("message");
		ms.sayHello("¹è¼öÁö");
		
	}
	
}
