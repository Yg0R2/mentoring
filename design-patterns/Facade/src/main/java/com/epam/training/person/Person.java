package com.epam.training.person;

public class Person implements Comparable {

    private int age;
    private int iq;
    private String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Person(String name, int age, int iq) {
        this(name, age);

        this.iq = iq;
    }

    @Override
    public int compareTo(Object o) {
        Person person = (Person) o;

        int nameCompare = this.getName().compareTo(person.getName());

        if (nameCompare != 0) {
            return nameCompare;
        }

        return Integer.valueOf(this.getAge()).compareTo(Integer.valueOf(person.getAge()));
    }

    public int getAge() {
        return age;
    }

    public int getIq() {
        return iq;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", iq=" + iq + "}";
    }

}
