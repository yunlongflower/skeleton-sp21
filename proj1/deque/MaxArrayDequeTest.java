package deque;

import org.junit.Test;

import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void simpleComparatorTest() {
        class IntegerComparator implements Comparator<Integer> {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(new IntegerComparator());
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addLast(7);
        mad.addFirst(1000);
        assertEquals(1000, (int) mad.max());
    }

    @Test
    public void addRemoveMaxTest() {
        class IntegerComparator implements Comparator<Integer> {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(new IntegerComparator());
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addLast(7);
        mad.addFirst(1000);
        assertEquals(1000, (int) mad.max());
        mad.removeFirst();
        assertEquals(7, (int) mad.max());
        mad.removeLast();
        assertEquals(2, (int) mad.max());
        mad.addLast(600);
        mad.addFirst(543);
        mad.removeLast();
        assertEquals(543, (int) mad.max());
    }

    @Test
    public void customComparatorTest() {
        class DefaultComparator implements Comparator<String> {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }

        class LengthComparator implements Comparator<String> {
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }

        MaxArrayDeque<String> mad = new MaxArrayDeque<String>(new DefaultComparator());
        mad.addFirst("aaaaaa");
        mad.addLast("bbb");
        assertEquals("bbb", mad.max());
        assertEquals("aaaaaa", mad.max(new LengthComparator()));
    }
}
