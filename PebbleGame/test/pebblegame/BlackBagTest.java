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
        System.out.println("Test takeRandomPebble with normal values");
        MockBlackBag instance = new MockBlackBag(1, "test/files/test_range.csv", 1);
        
        // The 4th item in the file is 15
        int expResult = 15;             
        
        int result = instance.takeRandomPebble();
        assertEquals(expResult, result);
    }

	/**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebbleEmptyBag() {
        System.out.println("Test takeRandomPebble with an empty Balck Bag and empty white bag");
        MockBlackBag instance = new MockBlackBag(1, "test/files/test_range.csv", 1);
        
        // When the bag cannot be replenished it returns -1 which should be 
		// dealt with earlier in the call
        int expResult = -1;              
        
        int result = instance.takeRandomPebble();
        assertEquals(expResult, result);
    }

	/**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebbleReplenish() {
        System.out.println("Test takeRandomPebble with an empty Balck Bag which is replenished by the white bag");
        MockBlackBag instance = new MockBlackBag(1, "test/files/test_range.csv", 1);
        
        // When the bag is replenished, the 4th pebble weight should be 7 
        int expResult = 7;              
        
        int result = instance.takeRandomPebble();
        assertEquals(expResult, result);
    }

    /**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbles() {
        System.out.println("replenishPebbles");
        MockBlackBag bBagInstance = new MockBlackBag(1,"test/files/test_range2.csv", 1);
		MockWhiteBag wBagInstance = new MockWhiteBag(1);
		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		bBagInitial = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagInitial = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

        bBagInstance.replenishPebbles();

		bBagResult = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagResult = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

		// the initial black bag should be empty
		assertEqual(new ArrayList<>(), bBagInitial);
		// The final black bag should be the same as the initial white bag
		assertEqual(wBagInitial, bBagResult);
		// the final white bag should be empty
		assertEqual(new ArrayList<>(), wBagResult);
    }

	/**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbleseEmptyFile() {
        System.out.println("replenishPebbles");
        MockBlackBag bBagInstance = new MockBlackBag(1,"test/files/test_range3.csv", 1);
		MockWhiteBag wBagInstance = new MockWhiteBag(1);
		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		bBagInitial = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagInitial = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

        bBagInstance.replenishPebbles();

		bBagResult = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagResult = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

		// the initial black bag should be empty
		assertEqual(new ArrayList<>(), bBagInitial);
		// the initial white bag should be empty
		assertEqual(new ArrayList<>(), bBagInitial);
		// The final black bag should be the empty since the inital white bag was empty
		assertEqual(new ArrayList<>(), bBagResult);
		// the final white bag should be empty
		assertEqual(new ArrayList<>(), wBagResult);
    }

    /**
     * Test of getTotalNumPebbles method, of class BlackBag.
     */
    @Test
    public void testGetTotalNumPebbles() {
        System.out.println("getTotalNumPebbles");
        MockBlackBag instance = new MockBlackBag(1,"test/files/test_range3.csv", 1);
        int expResult = 7;
        int result = instance.getTotalNumPebbles();
        assertEquals(expResult, result);
    }
    
}
