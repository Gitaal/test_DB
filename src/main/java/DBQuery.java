import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
  private static final String QUERY = "select id,uid,name,age from persons";

  public static void insertIntoTable(Person person) {
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("INSERT INTO persons (uid, name,age) " +
              "VALUES (" + person.getUid() + ",'" + person.getName() + "'," + person.getAge() + ")");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  /*
  public static void selectFromTable() {

    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(QUERY)) {

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        long uid = resultSet.getLong("uid");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        System.out.println(id + "," + uid + "," + name + "," + age);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }*/

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
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("delete from persons");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

 /* public static void createTable(){
    try (Connection connection = DBConnection.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("create table if not exists persons (id mediumint not null auto_increment, uid bigint, name char(255), age int, primary key(id))");

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }*/

}
