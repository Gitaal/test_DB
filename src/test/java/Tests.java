import dao.Person;
import dao.PersonDAO;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Tests {
  Logger log = Logger.getLogger(Tests.class.getName());

  @Test
  public void Task_1() {

    log.log(Level.INFO, "Выполняется тест по Task 1");
    Random random = new Random();
    int rows;
    int n = random.nextInt(10);
    for (int i = 0; i < n; i++) {
      Person person = new Person(random.nextInt(100), "Egorov" + "_" + i, random.nextInt(100));
      PersonDAO.insertIntoTable(person);
    }
    rows = PersonDAO.countRows();
    assertEquals(rows, n, "Не совпадают значения");
    log.log(Level.INFO,"Вколичество строк в таблице = количеству добавленных");
    log.log(Level.INFO, "Тест выполнен успешно!");

  }


  @AfterTest
  public void deleteRows() {
    PersonDAO.deleteFromTable();
  }
}
