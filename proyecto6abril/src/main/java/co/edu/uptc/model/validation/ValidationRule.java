package co.edu.uptc.model.validation;

import java.util.function.Predicate;

public class ValidationRule<T> {

    private final Predicate<T> condition;
    private final String errorKey;

    public ValidationRule(Predicate<T> condition, String errorKey) {
        this.condition = condition;
        this.errorKey = errorKey;
    }

    public void evaluate(T item) {
        if (!condition.test(item)) {
            throw new IllegalArgumentException(errorKey);
        }
    }
}