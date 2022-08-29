package com.exe.springJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CustomDAO2 {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
	
	public void insertData(CustomDTO dto) {
		//spring�� insert
		StringBuilder sql = new StringBuilder();
		
		/*
		sql.append("insert into custom (id,name,age) values(?,?,?)");
			
		jdbcTemplate.update(sql.toString(),
				dto.getId(),dto.getName(),dto.getAge());
		*/
		//namedJdbcTemplate
		//?��� :���� ó��
		sql.append("insert into custom (id,name,age) values (:id,:name,:age)");

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", dto.getId());
		params.addValue("name", dto.getName());
		params.addValue("age", dto.getAge());
		
		namedJdbcTemplate.update(sql.toString(), params);
		
	}
	
	public List<CustomDTO> getList() {
		//spring jdbc�� select
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,name,age from custom");
		//mapper=�ݺ���
		//rowmapper=interface,����Ŭ����
		//rs�� dto�� ��� dto�� �ٽ� lists��
		List<CustomDTO> lists = 
				jdbcTemplate.query(sql.toString(),
						new RowMapper<CustomDTO>() {

							public CustomDTO mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								
								CustomDTO dto = new CustomDTO();
								
								dto.setId(rs.getInt("id"));
								dto.setName(rs.getString("name"));
								dto.setAge(rs.getInt("age"));
								
								return dto;
							}
					
				});
		return lists;
	}
	
	public void updateData(CustomDTO dto) {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("update custom set name=?,age=? where id=?");
		
		jdbcTemplate.update(sql.toString(),
				dto.getName(),dto.getAge(),dto.getId());
		
	} 
	
	public CustomDTO getReadData(int id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,name,age from custom where id=?");
		//�ϳ��� �����͸� ������ ��-queryForObject
		CustomDTO dtoOne = 
				jdbcTemplate.queryForObject(sql.toString(), 
						new RowMapper<CustomDTO>() {

							public CustomDTO mapRow(ResultSet rs, int rowNum) 
									throws SQLException {

								CustomDTO dto = new CustomDTO();
								
								dto.setId(rs.getInt("id"));
								dto.setName(rs.getString("name"));
								dto.setAge(rs.getInt("age"));
								
								return dto;
							}
					
				},id);
		return dtoOne;
	}
	
	public void deleteData(int id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete custom where id=?");
			
		jdbcTemplate.update(sql.toString(),id);

	}
	
}