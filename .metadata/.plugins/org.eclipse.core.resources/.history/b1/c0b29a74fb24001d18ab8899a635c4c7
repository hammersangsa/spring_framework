package com.exe.springdi4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//객체생성(web에서는 controller)
@Component("serviceConsumer")
public class ServiceConsumer {
		
	//Autowired로 자동 의존성주입
	
	@Autowired
	@Qualifier("messageService")
	MessageService ms;
	
	@Autowired
	TimeService ts;
	
	@Autowired
	JobService js;
	
	public void consumerService() {
		
		String message = ms.getMessage();
		System.out.println(message);

		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
	
}
