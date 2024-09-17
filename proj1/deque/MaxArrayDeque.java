package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
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

    public T max() {
        return findMax(comparator);
    }

    public T max(Comparator<T> c) {
        return findMax(c);
    }
}
