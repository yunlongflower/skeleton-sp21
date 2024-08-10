package flik;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlik {

    @Test
    public void testIsSameNumber() {
        int a = 1000;
        int b = 1000;

        boolean r = Flik.isSameNumber(a, b);
        assertTrue(r);
    }
}
