package com.exe.springdi4;

import org.springframework.stereotype.Component;

@Component("jobjobService")
public class MyJobService implements JobService{

	public void getJob() {
		
		System.out.println("���� ���α׷��� �Դϴ�");
		
	}
}
