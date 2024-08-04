package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> simple = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        simple.addLast(4);
        buggy.addLast(4);
        simple.addLast(5);
        buggy.addLast(5);
        simple.addLast(6);
        buggy.addLast(6);

        for (int i = 0; i < 3; i++) {
            assertEquals(simple.removeLast(), buggy.removeLast());
        }
    }


    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                if (L.size() > 0) {
                    int l_last = L.getLast();
                    int buggy_last = buggy.getLast();
                    assertEquals(l_last, buggy_last);
                }
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    int l_last = L.removeLast();
                    int buggy_last = buggy.removeLast();
                    assertEquals(l_last, buggy_last);
                }
            }
        }
    }
}
