package com.exe.board.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);

	//Pageagble�� �Է¹޾Ƽ� Page<Question>Ÿ���� Object�� return;
	Page<Question> findAll(Pageable pageable);
	
	
}
