package com.exe.board.user;

import lombok.Getter;

@Getter
public enum UserRole {
	//비회원-guest
	//상수-->no setter
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	
}

/*
enum: 열거형 자료형(enumerated type)
열거형은 서로 연관된 상수들의 집합을 class로 만든 것

ex_
publicenum Fruit{
	APPLE, PEACH, BANANA;
}
publicenum Company{
	APPLE, GOOGLE, SAMSUNG;
}

Fruit type = Fruit.BANANA;

switch(type) {
case APPLE:
	System.out.print("사과입니다");break;
case PEACH:
	System.out.print("복숭아입니다");break;
case BANANA:
	System.out.print("바나나입니다");break;
*/