package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor //�����ε� �� ������ ��ü ����, ���� final�� ����
@Getter
//@Setter
public class HelloLombok {

	private final String name;
	private final int age;
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * HelloLombok hk = new HelloLombok("���γ�",40);//final �����ε�
	 * 
	 * //hk.setName("���γ�"); //hk.setAge(27);
	 * 
	 * System.out.println(hk.getName()); System.out.println(hk.getAge());
	 * 
	 * }
	 */
	
}
