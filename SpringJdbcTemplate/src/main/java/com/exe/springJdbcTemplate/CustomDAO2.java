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
		//spring의 insert
		StringBuilder sql = new StringBuilder();
		
		/*
		sql.append("insert into custom (id,name,age) values(?,?,?)");
			
		jdbcTemplate.update(sql.toString(),
				dto.getId(),dto.getName(),dto.getAge());
		*/
		//namedJdbcTemplate
		//?대신 :으로 처리
		sql.append("insert into custom (id,name,age) values (:id,:name,:age)");

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", dto.getId());
		params.addValue("name", dto.getName());
		params.addValue("age", dto.getAge());
		
		namedJdbcTemplate.update(sql.toString(), params);
		
	}
	
	public List<CustomDTO> getList() {
		//spring jdbc의 select
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,name,age from custom");
		//mapper=반복문
		//rowmapper=interface,무명클래스
		//rs를 dto에 담고 dto를 다시 lists로
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
		//하나의 데이터를 가져올 때-queryForObject
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
