package dao;

import dto.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {
  private static Logger logger = Logger.getLogger(PersonDAO.class.getName());
  private static final String INSERT_QUERY = "INSERT INTO persons (uid, name, age) Values (?, ?,?)";


  public static void insertIntoTable(Person person) {
    try {
      Connection connection = DBConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
      preparedStatement.setLong(1, person.getUid());
      preparedStatement.setString(2, person.getName());
      preparedStatement.setInt(3, person.getAge());

      int rows = preparedStatement.executeUpdate();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  public static int countRows() {
    int result = 0;
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select count(*) from persons");
      while (resultSet.next()) {
        result = resultSet.getInt(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return result;
  }

  public static void deleteFromTable() {
    logger.log(Level.INFO, "Записи удалены из БД.");
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("delete from persons");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }


}
