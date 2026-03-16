package co.edu.uptc.model.structures;

import co.edu.uptc.pojo.AccountingMovement;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class AccountingStack {

    private final Deque<AccountingMovement> stack = new ArrayDeque<>();
    private int nextId = 1;

    public void push(AccountingMovement movement) {
        stack.push(movement);
    }

    public AccountingMovement pop() {
        return stack.poll();
    }

    public List<AccountingMovement> getAll() {
        return new ArrayList<>(stack);
    }

    public double calculateTotal() {
        return stack.stream()
                .mapToDouble(m -> m.getType().name().equals("INCOME") ? m.getAmount() : -m.getAmount())
                .sum();
    }

    public int generateNextId() {
        return nextId++;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
