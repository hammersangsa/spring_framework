package com.exe.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//특정 조인포인트(포인트컷)에 적용할 코드
@Aspect
@Component
public class MyBeforeAdvice {

	@Before("execution(public void com..aop.*.*(..))")
	public void beforeMethodCall() {
		System.out.println("메소드 실행 전(Before Advice)");
	}
}
