package db.dao;

import db.DBConnection;
import db.dao.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {

  private static Logger LOGGER = Logger.getLogger(PersonDAO.class.getName());

  private static final String INSERT_QUERY = "insert into persons (uid, name, age) values (?, ?, ?)";
  private static final String SELECT_QUERY = "select uid, name, age from persons";
  private static final String SELECT_COUNT_QUERY = "select count(*) from persons";


  public static void insertIntoTable(Person person) {
    try (Connection connection = DBConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

      preparedStatement.setLong(1, person.getUid());
      preparedStatement.setString(2, person.getName());
      preparedStatement.setInt(3, person.getAge());

      preparedStatement.executeUpdate();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public static int countRows() {
    int result = 0;
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {

      ResultSet resultSet = statement.executeQuery(SELECT_COUNT_QUERY);

      while (resultSet.next()) {
        result = resultSet.getInt(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public static void deleteFromTable() {

    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {

      statement.executeUpdate("delete from persons");

      LOGGER.log(Level.INFO, "Записи удалены из БД.");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public static List<Person> selectFromTable() {
    List<Person> personList = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {

      ResultSet resultSet = statement.executeQuery(SELECT_QUERY);

      while (resultSet.next()) {
        long uid = resultSet.getLong("uid");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        personList.add(new Person(uid, name, age));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return personList;
  }

}
