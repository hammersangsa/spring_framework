package com.exe.springdi4;

public class ServiceConsumer {
		
	// �����ڷ� ������ ����
	MessageService ms;
	TimeService ts;
	JobService js;
	
	//�⺻������
	public ServiceConsumer() {}
	
	public ServiceConsumer(MessageService ms) {
		this.ms = ms;
	}
/*		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		MessageService ms = (MessageService)context.getBean("messageService");
*/		
	//�޼ҵ�� ������ ����	
	//�⺻ �����ڷ� ��ü ������ �޼ҵ���
	public void setTimeService(TimeService ts) {
		this.ts = ts;
	}
	//�޼ҵ�� ������ ����	
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
