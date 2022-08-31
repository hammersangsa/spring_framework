package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor //오버로딩 된 생성자 객체 생성, 변수 final로 선언
@Getter
//@Setter
public class HelloLombok {

	private final String name;
	private final int age;
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * HelloLombok hk = new HelloLombok("유인나",40);//final 오버로딩
	 * 
	 * //hk.setName("유인나"); //hk.setAge(27);
	 * 
	 * System.out.println(hk.getName()); System.out.println(hk.getAge());
	 * 
	 * }
	 */
	
}
