package co.edu.uptc.model;

import co.edu.uptc.model.structures.DoublyLinkedList;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class StrategyBasedCollectionManager<T> {

    private final DoublyLinkedList<T> list;
    private final BiConsumer<DoublyLinkedList<T>, T> insertStrategy;
    private final Function<DoublyLinkedList<T>, T> removeStrategy;

    public StrategyBasedCollectionManager(BiConsumer<DoublyLinkedList<T>, T> insertStrategy,
                             Function<DoublyLinkedList<T>, T> removeStrategy) {
        this.list = new DoublyLinkedList<>();
        this.insertStrategy = insertStrategy;
        this.removeStrategy = removeStrategy;
    }

    public void insertUsingStrategy(T element) {
        insertStrategy.accept(list, element);
    }

    public T removeUsingStrategy() {
        return removeStrategy.apply(list);
    }

    public List<T> getAll() {
        return list.getAll();
    }

    public void clear(){
        list.clear();
    }
}