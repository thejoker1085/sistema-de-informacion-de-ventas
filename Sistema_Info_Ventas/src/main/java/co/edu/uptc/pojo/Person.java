package co.edu.uptc.pojo;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String LastName;
    private char gender;
    private Date birthDate;

    public Person(int id, String name, String lastName, char gender, Date birthDate) {
        this.id = id;
        this.name = name;
        LastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
