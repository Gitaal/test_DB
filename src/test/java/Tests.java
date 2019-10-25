import db.dao.entity.Person;
import db.dao.PersonDAO;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Tests {

  private static final Logger LOGGER = Logger.getLogger(Tests.class.getName());
  private int RANDOM_PERSON_COUNT;

  @BeforeTest
  public void createPersons() {
    Random random = new Random();
    RANDOM_PERSON_COUNT = random.nextInt(10);
    for (int i = 0; i < RANDOM_PERSON_COUNT; i++) {
      Person person = new Person(random.nextInt(100), "Egorov" + "_" + i, random.nextInt(100));
      PersonDAO.insertIntoTable(person);
    }
  }

  @Test
  public void Task_1() {
    int rows;
    rows = PersonDAO.countRows();
    assertEquals(rows, RANDOM_PERSON_COUNT, "Не совпадают значения");
    LOGGER.log(Level.INFO, "Количество строк в таблице = количеству добавленных");
    LOGGER.log(Level.INFO, "Тест Task_1 выполнен успешно!");

  }

  @Test
  public void Task_2() {
    List<Person> person = PersonDAO.selectFromTable();

    int maxAge = person.stream().max(Comparator.comparingInt(Person::getAge)).get().getAge();
    person.stream().filter(p -> p.getAge() == maxAge).forEach(person1 -> LOGGER.log(Level.INFO, person1.getName()));

    LOGGER.log(Level.INFO, "Тест Task_2 выполнен успешно!");

  }

  @Test
  public void Task_3() {
    List<Person> person = PersonDAO.selectFromTable();
    Set<Person> personSet = new TreeSet<>(person);

    personSet.stream().forEach(System.out::println);
    LOGGER.log(Level.INFO, "Тест Task_2 выполнен успешно!");

  }

  @AfterTest
  public void deleteRows() {
    PersonDAO.deleteFromTable();
  }
}
