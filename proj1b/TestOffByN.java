import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author q
 * @create 2020-08-27 12:48
 */
public class TestOffByN {
    @Test
    public void testOffByN(){
        OffByN ofn = new OffByN(5);
        assertTrue(ofn.equalChars('a','f'));
        assertFalse(ofn.equalChars('a','c'));
    }
}
