package co.edu.uptc.pojo;

import java.time.LocalDate;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;

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

    public String toCsvRow() {
        return id + "," + firstName + "," + lastName + "," + gender + "," + birthDate;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-20s %-20s %-10s %s", id, firstName, lastName, gender, birthDate);
    }
}
