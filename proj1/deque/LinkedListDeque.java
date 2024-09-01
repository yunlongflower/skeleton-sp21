package deque;

public class LinkedListDeque<T> {

    private static class Node<T> {
       T item;
       Node<T> previous, next;
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>();
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<T>();
        node.item = item;
        node.previous = sentinel;
        node.next = sentinel.next;
        sentinel.next.previous = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> node = new Node<T>();
        node.item = item;
        node.previous = sentinel.previous;
        node.next = sentinel;
        sentinel.previous.next = node;
        sentinel.previous = node;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (Node<T> node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.item);
            if (node.next != sentinel) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = sentinel.next;
        node.next.previous = sentinel;
        sentinel.next = node.next;
        size -= 1;
        return node.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = sentinel.previous;
        node.previous.next = sentinel;
        sentinel.previous = node.previous;
        size -= 1;
        return node.item;
    }

    public  T get(int index) {
        if (size <= index) {
            return null;
        }
        Node<T> node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }
}