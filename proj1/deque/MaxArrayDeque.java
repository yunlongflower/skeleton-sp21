package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private T maxValue;
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        maxValue = null;
        comparator = c;
    }

    @Override
    public void addFirst(T item) {
        if (isEmpty() || comparator.compare(item, max_value) > 0) {
            maxValue = item;
        }
        super.addFirst(item);
    }

    @Override
    public void addLast(T item) {
        if (isEmpty() || comparator.compare(item, max_value) > 0) {
            maxValue = item;
        }
        super.addLast(item);
    }

    private T findMax(Comparator<T> c) {
        T max = null;
        for (int i = 0; i < size(); i++) {
            T item = get(i);
            if (max == null || c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

    @Override
    public T removeFirst() {
        T result = super.removeFirst();
        if (result == maxValue) {
            maxValue = findMax(comparator);
        }
        return result;
    }

    public T removeLast() {
        T result = super.removeLast();
        if (result == maxValue) {
            maxValue = findMax(comparator);
        }
        return result;
    }

    public T max() {
        return maxValue;
    }

    public T max(Comparator<T> c) {
        return findMax(c);
    }
}
