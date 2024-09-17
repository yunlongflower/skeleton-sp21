package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

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

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<T>();
        node.item = item;
        node.previous = sentinel;
        node.next = sentinel.next;
        sentinel.next.previous = node;
        sentinel.next = node;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> node = new Node<T>();
        node.item = item;
        node.previous = sentinel.previous;
        node.next = sentinel;
        sentinel.previous.next = node;
        sentinel.previous = node;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (Node<T> node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.item);
            if (node.next != sentinel) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
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

    public T getRecursive(int index) {
        return getNth(sentinel.next, index);
    }

    private T getNth(Node<T> head, int index) {
        if (head == sentinel) {
            return null;
        }
        if (index == 0) {
            return head.item;
        }
        return getNth(head.next, index - 1);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node<T> p;
        private LinkedListDequeIterator() {
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            T result = p.item;
            p = p.next;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!(get(i).equals(other.get(i)))) {
                return false;
            }
        }
        return true;
    }
}