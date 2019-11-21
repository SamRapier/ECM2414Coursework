package pebblegame;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


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
        System.out.println("Test addPebble");
        int newPebbleWeight = 15;
        
        // adds a pebble of weight 15 to the bag
        wBag.addPebble(newPebbleWeight);
        
        // loads the pebble array and puts the value of the last pebble in the
        // bag into result variable
        List<Integer> pebbleArr = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);
        int result = pebbleArr.get(pebbleArr.size() - 1);

        // checks if the last result in the array is equal to the new pebble weight
        assertEquals(newPebbleWeight, result);
		System.out.println("    Test passed");
    }
	    
}
 