package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] elements;
    private int size, first, last;
    private static final int INITLENGTH = 8;

    public ArrayDeque() {
        elements = (T[]) new Object[INITLENGTH];
        size = 0;
        first = 0;
        last = 0;
    }

    private void resize() {
        T[] tmp = (T[]) new Object[size * 2];
        for (int i = 0; i < elements.length; i++) {
            tmp[i] = get(i);
        }
        first = tmp.length - 1;
        last = size - 1;
        elements = tmp;
    }

    @Override
    public void addFirst(T item) {
        if (size == elements.length) {
            resize();
        }
        elements[first] = item;
        first = (first - 1 + elements.length) % elements.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == elements.length) {
            resize();
        }
        last = (last + 1) % elements.length;
        elements[last] = item;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = (first + 1) % elements.length; i <= last; i = (i + 1) % elements.length) {
            System.out.print(elements[i]);
            if (i < last) {
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
        first = (first + 1) % elements.length;
        T result = elements[first];
        elements[first] = null;
        size -= 1;
        return result;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = elements[last];
        elements[last] = null;
        last = (last - 1 + elements.length) % elements.length;
        size -= 1;
        return result;
    }

    @Override
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        index = (first + 1 + index) % elements.length;
        return elements[index];
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T result = elements[(first + 1 + index) % elements.length];
            index += 1;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
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
