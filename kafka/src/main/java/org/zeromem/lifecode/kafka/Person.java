package org.zeromem.lifecode.kafka;

import java.util.Objects;

/**
 * @author zeromem
 * @date 2018/3/14
 */
public class Person {
    public String name;
    public int age;
    public String info;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(info, person.info);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, info);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", info='" + info + '\'' +
                '}';
    }


}
