package co.edu.uptc.pojo;

import java.time.LocalDate;
import java.time.Period;

import co.edu.uptc.interfaces.Csvable;

public class Person implements Csvable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;

    public Person(int id, String firstName, String lastName, Gender gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Gender getGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toCsvRow() {
        return id + "," + firstName + "," + lastName + "," + gender + "," + birthDate;
    }
}
