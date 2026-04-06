package co.edu.uptc.model;

import co.edu.uptc.model.structures.DoublyLinkedList;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class DataStructureStrategyFactory {

    public static <T> BiConsumer<DoublyLinkedList<T>, T> queueAppendStrategy() {
        return DoublyLinkedList::add;
    }

    public static <T> BiConsumer<DoublyLinkedList<T>, T> stackPushStrategy() {
        return DoublyLinkedList::addFront;
    }

    public static <T> Function<DoublyLinkedList<T>, T> queueDequeueStrategy() {
        return DoublyLinkedList::removeFirst;
    }

    public static <T> Function<DoublyLinkedList<T>, T> listRemoveLastStrategy() {
        return DoublyLinkedList::removeLast;
    }

    private DataStructureStrategyFactory() {}
}