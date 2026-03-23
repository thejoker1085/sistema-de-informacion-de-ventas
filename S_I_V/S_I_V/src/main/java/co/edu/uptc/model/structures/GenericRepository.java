package co.edu.uptc.model.structures;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T> {
    
    private GenericNode<T> head;
    private GenericNode<T> tail;
    private int size;
    private int nextId;

    public GenericRepository() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.nextId = 1;
    }

    public void add(T data) {
        GenericNode<T> newNode = new GenericNode<>(data);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void addFront(T data) {
        GenericNode<T> newNode = new GenericNode<>(data);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).getData();
    }

    private GenericNode<T> getNode(int index) {
        GenericNode<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.getPrevious();
            }
        }
        return node;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista esta vacia");
        }
        return head.getData();
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista esta vacia");
        }
        T data = head.getData();
        
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return data;
    }

    public T remove(int index) {
        validateIndex(index);
        GenericNode<T> node = getNode(index);
        T data = node.getData();
        removeNodeLinks(node);
        size--;
        return data;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void removeNodeLinks(GenericNode<T> node) {
        if (size == 1) {
            head = null;
            tail = null;
        } else if (node == head) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (node == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
    }

    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        GenericNode<T> current = head;
        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }
        return result;
    }

    public List<T> getAllReverse() {
        List<T> result = new ArrayList<>();
        GenericNode<T> current = tail;
        while (current != null) {
            result.add(current.getData());
            current = current.getPrevious();
        }
        return result;
    }

    public int generateNextId() {
        return nextId++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
        nextId = 1;
    }
}