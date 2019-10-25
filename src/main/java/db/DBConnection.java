package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

  private static Properties props;
  private static final String PROPERTY_FILE_NAME = "db.properties";

  static {

    props = new Properties();

    try {

      InputStream is = DBConnection.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
      props.load(is);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {

    Connection con = null;

    try {

      Class.forName(props.getProperty("DB_DRIVER_CLASS"));

      con = DriverManager.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"), props.getProperty("DB_PASSWORD"));

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return con;
  }
}
