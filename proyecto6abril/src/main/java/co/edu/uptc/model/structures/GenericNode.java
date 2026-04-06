package co.edu.uptc.model.structures;

public class GenericNode<T> {

    private T data;
    private GenericNode<T> next;
    private GenericNode<T> previous;

    public GenericNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getData() { 
        return data; 
    }

    public void setData(T data) {
        this.data = data; 
    }

    public GenericNode<T> getNext() {
         return next; 
    }

    public void setNext(GenericNode<T> next) { 
        this.next = next; 
    } 

    public GenericNode<T> getPrevious() { 
        return previous;
    }
    public void setPrevious(GenericNode<T> previous) { 
        this.previous = previous; 
    }
}