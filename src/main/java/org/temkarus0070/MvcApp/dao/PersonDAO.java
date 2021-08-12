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
public class PersonDAO {
    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    private static int PEOPLE_COUNT;
    private static final String URL="jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="postgres";
    private static Connection connection;
    static{
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            String query=String.format("SELECT MAX(Id) from Person");
            int max =statement.executeQuery(query).getInt(0);
            PEOPLE_COUNT=max+10;
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
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
