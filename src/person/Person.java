package person;

import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    public Integer age;

    public Person(String name, Integer age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Имя: " + name + " Возраст: " + age.toString();
    }
}
