package com.jdbc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.jdbc.dto.BoardDTO;

public class BoardDAO {

	private SqlSessionTemplate sessionTemplate;
	
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) throws Exception{
		this.sessionTemplate = sessionTemplate;
	}
		
	//num�� �ִ�
	public int getMaxNum() {
		
		int maxNum = 0;
		
		maxNum = sessionTemplate.selectOne("com.boardMapper.maxNum");
		
		return maxNum;
	}
	//�Է�
	public void insertData(BoardDTO dto) {
		
		sessionTemplate.insert("com.boardMapper.insertData",dto);
		
	}
	//��ü������ ��������(4���� ����)
	public List<BoardDTO> getLists(int start, int end, 
			String searchKey,String searchValue){
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("start", start);
		params.put("end", end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);

		List<BoardDTO> lists = 
				sessionTemplate.selectList("com.boardMapper.getLists",params);
		
		return lists;
	}
	//��ü�������� ����
	public int getDataCount(String searchKey,String searchValue) {
		
		int dataCount = 0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		dataCount = 
				sessionTemplate.selectOne("com.boardMapper.getDataCount",params);
		
		return dataCount;
	}
	//num���� �Ѱ��� ������ ��������
	public BoardDTO getReadData(int num) {
		
		BoardDTO dto = 
				sessionTemplate.selectOne("com.boardMapper.getReadData",num);

		return dto;
	}
	//��ȸ�� ����
	public void updateHitCount(int num) {
		
		sessionTemplate.update("com.boardMapper.updateHitCount",num);
		
	}
	//����
	public void updateData(BoardDTO dto) {
		
		sessionTemplate.update("com.boardMapper.updateData",dto);
		
	}
	//����
	public void deleteData(int num) {
		
		sessionTemplate.delete("com.boardMapper.deleteData",num);
		
	}
}