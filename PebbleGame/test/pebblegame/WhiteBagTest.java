package pebblegame;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samra
 */
public class WhiteBagTest {
    public static WhiteBag wBag;
    
    public WhiteBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        wBag = new WhiteBag(1);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addPebble method, of class WhiteBag.
     */
    @Test
    public void testAddPebble() {
        System.out.println("addPebble");
        int newPebbleWeight = 15;
        
        wBag.addPebble(newPebbleWeight);
        
        List<Integer> pebbleArr = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);
        int result = pebbleArr.get(pebbleArr.size() - 1);

        assertEquals(newPebbleWeight, result);
    }
	    
}
 