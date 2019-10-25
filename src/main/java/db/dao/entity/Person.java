package db.dao.entity;

public class Person implements Comparable<Person> {

  private long uid;
  private String name;
  private int age;

  public Person(long uid, String name, int age) {
    this.uid = uid;
    this.name = name;
    this.age = age;

  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person {" +
            "uid = " + uid +
            ", name = '" + name + '\'' +
            ", age = " + age +
            '}';
  }

  @Override
  public int compareTo(Person o) {
    if (age == o.age)
      return 0;
    else if (age > o.age)
      return 1;
    else
      return -1;
  }
}
