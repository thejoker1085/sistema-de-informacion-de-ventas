package co.edu.uptc.model.structures;

import co.edu.uptc.pojo.Person;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class PersonQueue {

    private final Deque<Person> queue = new ArrayDeque<>();
    private int nextId = 1;

    public void enqueue(Person person) {
        queue.addLast(person);
    }

    public Person dequeue() {
        return queue.pollFirst();
    }

    public List<Person> getAll() {
        return new ArrayList<>(queue);
    }

    public int generateNextId() {
        return nextId++;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
