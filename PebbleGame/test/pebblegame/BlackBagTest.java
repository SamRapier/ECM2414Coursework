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
public class BlackBagTest {
    
    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebble() {
        System.out.println("takeRandomPebble");
        MockBlackBag instance = new MockBlackBag(1, 1);
        
        // The 4th item in the file is 15
        int expResult = 15;

        List<Integer> pebbleArr = new ArrayList<>();
        pebbleArr = instance.loadPebbles();              
        
        int result = instance.takeRandomPebble();
        assertEquals(expResult, result);
    }

    /**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbles() {
        System.out.println("replenishPebbles");
        MockBlackBag instance = new MockBlackBag(1, 1);

        instance.replenishPebbles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalNumPebbles method, of class BlackBag.
     */
    @Test
    public void testGetTotalNumPebbles() {
        System.out.println("getTotalNumPebbles");
        MockBlackBag instance = new MockBlackBag(1, 1);
        int expResult = 7;
        int result = instance.getTotalNumPebbles();
        assertEquals(expResult, result);
    }
    
}
