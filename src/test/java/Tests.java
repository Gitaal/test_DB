import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Tests {


  @Test
  public void Task_1() {
    System.out.println("Выполняется тест по Task 1");
    Random random = new Random();
    int rows;
    int n = random.nextInt(10);
    for (int i = 0; i < n; i++) {
      Person person = new Person(random.nextInt(100), "Egorov" + "_" + i, random.nextInt(100));
      DBQuery.insertIntoTable(person);
    }
    rows = DBQuery.countRows();
    System.out.println("Выполняется сравнение добавленных строк n = " + n + " с количеством строк в таблице persons = " + rows);
    assertEquals(rows, n, "Не совпадают значения");
    System.out.println("Тест выполнен успешно!");

  }


  @AfterTest
  public void deleteRows() {
    DBQuery.deleteFromTable();
  }
}
