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
        MockBlackBag instance = new MockBlackBag(1, "test/files/test_range2.csv", 1);
        
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
        MockBlackBag instance = new MockBlackBag(1, "test/files/test_range2.csv", 1);
        MockWhiteBag wBaginstance = new MockWhiteBag(1);

		wBaginstance.addPebble(4);
		wBaginstance.addPebble(5);
		wBaginstance.addPebble(6);
		wBaginstance.addPebble(7);
		wBaginstance.addPebble(8);
		wBaginstance.addPebble(9);

        // When the bag is replenished, the 5th pebble weight should be 8
        int expResult = 8;              
        
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
		wBagInstance.addPebble(4);
		wBagInstance.addPebble(5);
		wBagInstance.addPebble(6);
		wBagInstance.addPebble(7);
		wBagInstance.addPebble(8);
		wBagInstance.addPebble(9);

		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		bBagInitial = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagInitial = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

        bBagInstance.replenishPebbles();

		bBagResult = bBagInstance.loadPebbles(bBagInstance.STORAGE_FILE_LOCATION);
		wBagResult = wBagInstance.loadPebbles(wBagInstance.STORAGE_FILE_LOCATION);

		// the initial black bag should be empty
		assertEquals(new ArrayList<>(), bBagInitial);
		// The final black bag should be the same as the initial white bag
		assertEquals(wBagInitial, bBagResult);
		// the final white bag should be empty
		assertEquals(new ArrayList<>(), wBagResult);
    }

	/**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbleseEmptyFile() {
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
		assertEquals(new ArrayList<>(), bBagInitial);
		// the initial white bag should be empty
		assertEquals(new ArrayList<>(), bBagInitial);
		// The final black bag should be the empty since the inital white bag was empty
		assertEquals(new ArrayList<>(), bBagResult);
		// the final white bag should be empty
		assertEquals(new ArrayList<>(), wBagResult);
    }

    /**
     * Test of getTotalNumPebbles method, of class BlackBag.
     */
    @Test
    public void testGetTotalNumPebbles() {
        System.out.println("getTotalNumPebbles");
        MockBlackBag instance = new MockBlackBag(1,"test/files/test_range.csv", 1);
        int expResult = 25;
        int result = instance.getTotalNumPebbles();
        assertEquals(expResult, result);
    }
    
}
