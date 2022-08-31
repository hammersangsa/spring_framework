package com.exe.board;

import org.springframework.data.jpa.repository.JpaRepository;

//������ ó���� ���ؼ��� ���� �����ͺ��̽��� �����ϴ�
//JPA�� Repository�� �ʿ�

//��ƼƼ���� ������ �����ͺ��̽� ���̺� �����ϴ� �޼ҵ带
//����ϱ����� �������̽��̴�(findAll,save)
//CRUD(create,read,update,delete)�� �����ϴ� ����
//�ڷ���(Ű��)
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	//subject�� ã�� ����
	//findBy+entity
	Question findBySubject(String subject);
	
}
