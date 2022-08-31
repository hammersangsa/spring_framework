package com.exe.board;

import org.springframework.data.jpa.repository.JpaRepository;

//데이터 처리를 위해서는 실제 데이터베이스와 연동하는
//JPA의 Repository가 필요

//엔티티에의 생성된 데이터베이스 테이블에 접근하는 메소드를
//사용하기위한 인터페이스이다(findAll,save)
//CRUD(create,read,update,delete)를 정의하는 계층
//자료형(키값)
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	//subject로 찾는 쿼리
	//findBy+entity
	Question findBySubject(String subject);
	
}
