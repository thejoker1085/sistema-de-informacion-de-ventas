package co.edu.uptc.model.service;

import co.edu.uptc.model.StrategyBasedCollectionManager;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.validation.Validator;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.Person;

import java.time.LocalDate;
import java.util.List;

public class PersonService {

    private final StrategyBasedCollectionManager<Person> manager;
    private final Validator<Person> validator;
    private final CsvWriter csvWriter;
    private int currentId = 1;

    public PersonService(StrategyBasedCollectionManager<Person> manager, Validator<Person> validator, CsvWriter csvWriter) {
        this.manager = manager;
        this.validator = validator;
        this.csvWriter = csvWriter;
    }

    public void registerPersonInQueue(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        Person person = new Person(currentId++, firstName, lastName, gender, birthDate);
        validator.validate(person);
        manager.insertUsingStrategy(person);
    }

    public Person dequeueFirstPerson() {
        Person removed = manager.removeUsingStrategy();
        if (removed != null) {
            recalculateIds();
        }
        return removed;
    }
    
    public List<Person> retrieveAllPersons() {
        return manager.getAll();
    }

    public void exportToCsv(String filePath) {
        String csvContent = CsvFormatter.formatPersons(manager.getAll());
        csvWriter.write(filePath, csvContent);
    }
    
    private void recalculateIds() {
        List<Person> persons = manager.getAll();
        manager.clear();
        int newId = 1;
        for (Person p : persons) {
            Person newPerson = new Person(newId++, p.getFirstName(), p.getLastName(), p.getGender(), p.getBirthDate());
            manager.insertUsingStrategy(newPerson);
        }
        currentId = newId;
    }
}