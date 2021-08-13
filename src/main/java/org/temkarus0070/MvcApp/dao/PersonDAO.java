package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.dao.Mappers.PersonMapper;
import org.temkarus0070.MvcApp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO  {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate();

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public PersonDAO(){}


    public List<Person> index(){
        return jdbcTemplate.query("SELECT * From Person",new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person show(int id){
      return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<Person>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)", person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id,Person person){
        jdbcTemplate.update("UPDATE Person SET name=?,email=?,age=? where id=?",new Object[]{person.getName(),person.getEmail(),person.getAge(),person.getId()});
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE Person WHERE id=?",id);

    }
}
