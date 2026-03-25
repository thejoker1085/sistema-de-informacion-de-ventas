package co.edu.uptc.model.service;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.model.ValidationService;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.structures.DoublyLinkedList;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.Person;

import java.time.LocalDate;
import java.util.List;

public class PersonService {

    private final DoublyLinkedList<Person> repository = new DoublyLinkedList<>();
    private final ValidationService validator;
    private final CsvWriter csvWriter;

    public PersonService(ValidationService validator, CsvWriter csvWriter) {
        this.validator = validator;
        this.csvWriter = csvWriter;
    }

    public void add(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        validatePerson(firstName, lastName);
        repository.add(new Person(repository.generateNextId(), firstName, lastName, gender, birthDate));
    }

    private void validatePerson(String firstName, String lastName) {
        if (!validator.isValidFirstName(firstName))
            throw new IllegalArgumentException("msg.error.name:" + validator.getNameMin() + ":" + validator.getNameMax());
        if (!validator.isValidLastName(lastName))
            throw new IllegalArgumentException("msg.error.lastname:" + validator.getLastNameMin() + ":" + validator.getLastNameMax());
    }

    public Person removeFirst() {
        if (repository.isEmpty()) throw new IllegalStateException("msg.error.empty");
        return repository.removeFirst();
    }

    public List<Person> getAll() {
        return repository.getAll();
    }

    public void exportToCsv() {
        csvWriter.write(ConfigLoader.get("persons.file"), CsvFormatter.formatPersons(repository.getAll()));
    }
}