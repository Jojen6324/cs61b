import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {

    @Test
    public void testEqual() {
        int i = 256;
        int j = 256;
        assertTrue(i == j);
        assertTrue(Flik.isSameNumber(i, j));
    }
}