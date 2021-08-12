package org.temkarus0070.MvcApp.dao;

import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
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
        List<Person> people=new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL="SELECT * FROM person";
            ResultSet resultSet= statement.executeQuery(SQL);
            while (resultSet.next()){
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        }
        catch (SQLException sqlException){
sqlException.printStackTrace();
        }
        return people;
    }

    public Person show(int id){
        Person person=null;
  try{
      PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from Person where id=?");
      preparedStatement.setInt(1,id);
      ResultSet resultSet=preparedStatement.executeQuery();
      person=new Person();
      resultSet.next();
      person.setId(resultSet.getInt("id"));
      person.setAge(resultSet.getInt("age"));
      person.setEmail(resultSet.getString("email"));
      person.setName(resultSet.getString("name"));
  }
  catch (SQLException sqlException){
      sqlException.printStackTrace();
  }
        return person;
    }

    public void save(Person person){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.executeQuery();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public void update(int id,Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?, email=?, age=? where id=?");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getEmail());
            preparedStatement.setInt(3,person.getAge());
            preparedStatement.setInt(4,person.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void delete(int id){
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement=connection.prepareStatement("" +
                    "DELETE Person where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
