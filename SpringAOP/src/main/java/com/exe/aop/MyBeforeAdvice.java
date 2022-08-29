package com.exe.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Ư�� ��������Ʈ(����Ʈ��)�� ������ �ڵ�
@Aspect
@Component
public class MyBeforeAdvice {

	@Before("execution(public void com..aop.*.*(..))")
	public void beforeMethodCall() {
		System.out.println("�޼ҵ� ���� ��(Before Advice)");
	}
}
