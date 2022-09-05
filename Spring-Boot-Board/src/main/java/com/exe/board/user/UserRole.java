package com.exe.board.user;

import lombok.Getter;

@Getter
public enum UserRole {
	//��ȸ��-guest
	//���-->no setter
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	
}

/*
enum: ������ �ڷ���(enumerated type)
�������� ���� ������ ������� ������ class�� ���� ��

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
	System.out.print("����Դϴ�");break;
case PEACH:
	System.out.print("�������Դϴ�");break;
case BANANA:
	System.out.print("�ٳ����Դϴ�");break;
*/