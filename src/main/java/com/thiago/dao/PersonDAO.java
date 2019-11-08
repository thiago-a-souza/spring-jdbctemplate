package com.thiago.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.thiago.model.Person;

@Service
public class PersonDAO {
	private final String SQL_FIND_ALL = "select * from person";
	private final String SQL_FIND_BY_ID = "select * from person where id = ?";
	private final String SQL_DELETE_BY_ID = "delete from person where id = ?";
	private final String SQL_UPDATE_BY_ID = "update person set first_name = ?, last_name = ? where id = ?";
	private final String SQL_INSERT = "insert into person (id, first_name, last_name) values (?, ?, ?)";
	private final String SQL_COUNT = "select count(*) from person where id = ?";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Person> findAll(){
		return jdbcTemplate.query(SQL_FIND_ALL, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findById(Long id) {
		return  jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {id}, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public boolean deleteById(Long id) {
		return jdbcTemplate.update(SQL_DELETE_BY_ID, id) > 0;
	}
	
	public boolean update(Person person) {
		return jdbcTemplate.update(SQL_UPDATE_BY_ID, person.getFirstName(), person.getLastName(), person.getId()) > 0;
	}
	
	public boolean insert(Person person) {
		return jdbcTemplate.update(SQL_INSERT, person.getId(), person.getFirstName(), person.getLastName()) > 0;
	}
	
	public int count(long id) {
		return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class, id);
	}
}
