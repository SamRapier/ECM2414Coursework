package pebblegame;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author samra
 */
public class BlackBagTest {
    
	private static BlackBag bBag;
	private static WhiteBag wBag;
	private static MockRandom mockRandom;

    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        mockRandom = new MockRandom();
        bBag = new BlackBag(1, "test/files/test_range.csv", 1, mockRandom);
        wBag = new WhiteBag(1);
    }
    
    @AfterClass
    public static void tearDownClass() {
        bBag = null;
        wBag = null;
        mockRandom = null;
    }

    @Before
    public void setUpMethod(){
		bBag.savePebbles(bBag.loadPebbles("test/files/test_range.csv"), bBag.STORAGE_FILE_LOCATION);
		wBag = new WhiteBag(1);
		mockRandom = new MockRandom();
		mockRandom.sequentialNum = 0;
    }

    /**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebble() {
        assertEquals(2, bBag.takeRandomPebble());
        assertEquals(4, bBag.takeRandomPebble());
        assertEquals(6, bBag.takeRandomPebble());
        assertEquals(8, bBag.takeRandomPebble());
        assertEquals(10, bBag.takeRandomPebble());
    }

	/**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebbleEmptyBag() {
		bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);
        
        // When the bag cannot be replenished it returns -1 which should be 
		// dealt with earlier in the call
        int expResult = -1;              
        
        int result = bBag.takeRandomPebble();
        assertEquals(expResult, result);
    }

	/**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebbleReplenish() {
        System.out.println("Test takeRandomPebble with an empty Balck Bag which is replenished by the white bag");
        bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);

        wBag.addPebble(4);
        wBag.addPebble(5);
        wBag.addPebble(6);
        wBag.addPebble(7);
        wBag.addPebble(8);
        wBag.addPebble(9);

        // When the bag is replenished, pebble weight taken should be the first one and that is 4
        int expResult = 4;              
        
        int result = bBag.takeRandomPebble();
        assertEquals(expResult, result);
    }

    /**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbles() {
        System.out.println("replenishPebbles");
        bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);
        
		wBag.addPebble(4);
		wBag.addPebble(5);
		wBag.addPebble(6);
		wBag.addPebble(7);
		wBag.addPebble(8);
		wBag.addPebble(9);

		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		bBagInitial = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagInitial = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

        bBag.replenishPebbles();

		bBagResult = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagResult = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

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
		bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);

		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		bBagInitial = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagInitial = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

        bBag.replenishPebbles();

		bBagResult = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagResult = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

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
        
        int expResult = 25;
        int result = bBag.getTotalNumPebbles();
        assertEquals(expResult, result);
    }
    
}
