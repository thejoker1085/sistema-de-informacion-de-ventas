package co.edu.uptc.model.validation;

import java.util.List;

public class Validator<T> {

    private final List<ValidationRule<T>> rules;

    public Validator(List<ValidationRule<T>> rules) {
        this.rules = rules;
    }

    public void validate(T item) {
        rules.forEach(rule -> rule.evaluate(item));
    }
}