package com.exe.springmybatis;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomMain {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext("app-context.xml");
		
		CustomDAO dao = (CustomDAO)context.getBean("customDAO");
		
		CustomDTO dto;
		
		//insert
		/*
		dto = new CustomDTO(); dto.setId(666); dto.setName("베배배"); dto.setAge(28);
	 
		dao.insertData(dto); System.out.println("insert 완료");
		
	
		
		//update
		dto = new CustomDTO();
		dto.setId(333);
		dto.setName("배뱀배");
		dto.setAge(25);
		
		dao.updateData(dto);
		System.out.println("update 완료");
		
		
		//delete
		//dao.deleteData(222);
		
		
		//oneselect
		dto = dao.getReadData(111);
		if(dto!=null) {
			System.out.printf("%d %s %d\n", 
					dto.getId(),dto.getName(),dto.getAge());
		}
		System.out.println("oneselect 완료");
		*/

		//list
		List<CustomDTO> lists = dao.getList();
		for(CustomDTO dto1 : lists) {
			System.out.printf("%d %s %d\n", 
					dto1.getId(),dto1.getName(),dto1.getAge());
		}
		System.out.println("select 완료");
		
	}

}
