package com.exe.springdi4;

public class ServiceConsumer {
		
	// 생성자로 의존성 주입
	MessageService ms;
	TimeService ts;
	JobService js;
	
	//기본생성자
	public ServiceConsumer() {}
	
	public ServiceConsumer(MessageService ms) {
		this.ms = ms;
	}
/*		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		MessageService ms = (MessageService)context.getBean("messageService");
*/		
	//메소드로 의존성 주입	
	//기본 생성자로 객체 생성후 메소드사용
	public void setTimeService(TimeService ts) {
		this.ts = ts;
	}
	//메소드로 의존성 주입	
	public void setJobService(JobService js) {
		this.js = js;
	}
	
	public void consumerService() {
		
		String message = ms.getMessage();
		System.out.println(message);

		String time = ts.getTimeString();
		System.out.println(time);
		
		js.getJob();
		
	}
	
}
