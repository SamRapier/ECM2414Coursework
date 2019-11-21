package pebblegame;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class BlackBagTest {
    
	// declare variables
	private static BlackBag bBag;
	private static WhiteBag wBag;
	private static MockRandom mockRandom;

    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
		// initialise the variables
		// make a mockRandom object to use instead of normal random so we can predict the output
        mockRandom = new MockRandom();
		// create a new black bag and white bag
        bBag = new BlackBag(1, "test/files/test_range.csv", 1, mockRandom);
        wBag = new WhiteBag(1);
    }
    
    @AfterClass
    public static void tearDownClass() {
		// set the variables to null when the class is finished
        bBag = null;
        wBag = null;
        mockRandom = null;
    }

    @Before
    public void setUpMethod(){
		// Before each method, load and save the pebbles from the test_range file
		bBag.savePebbles(bBag.loadPebbles("test/files/test_range.csv"), bBag.STORAGE_FILE_LOCATION);
		// reset the white bag and random object
		wBag = new WhiteBag(1);
		mockRandom = new MockRandom();
    }

    /**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebble() {
		// test the take random pebble multiple times
		// the random number will increment by 1 for each ittereation so that is why it outputs every even number
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
		System.out.println("Test takeRandomPebble with an empty Balck Bag and an empty white bag:");
		System.out.println("    expected result = -1");
		// empty the black bag file
		bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);
        
        // When the bag cannot be replenished it returns -1 which should be 
		// dealt with earlier in the call
        int expResult = -1;              
        
		// run the method and save the result
        int result = bBag.takeRandomPebble();

		// check that the result and excpeted result are the same
        assertEquals(expResult, result);
		System.out.println("    Test passed");
    }

	/**
     * Test of takeRandomPebble method, of class BlackBag.  
     */
    @Test
    public void testTakeRandomPebbleReplenish() {
        System.out.println("Test takeRandomPebble with an empty Balck Bag which is replenished by the white bag");
		System.out.println("    expected result = 4");

		// empty the black bag
        bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);

		// add a bunch of pebbles to the white bag
        wBag.addPebble(4);
        wBag.addPebble(5);
        wBag.addPebble(6);
        wBag.addPebble(7);
        wBag.addPebble(8);
        wBag.addPebble(9);

        // When the bag is replenished, the mock random number will pick the first item, which is 4
        int expResult = 4;              
        
		// run the module to get the result
        int result = bBag.takeRandomPebble();
        assertEquals(expResult, result);
		System.out.println("    Test passed");
    }

    /**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbles() {
        System.out.println("Test replenishPebbles with an empty black bag and a white bag with pebbles in");
		System.out.println("    The final black bag should be equal to the initial white bag");

		// empty the black bag
        bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);
        
		// add a bunch of pebbles to the white bag
		wBag.addPebble(4);
		wBag.addPebble(5);
		wBag.addPebble(6);
		wBag.addPebble(7);
		wBag.addPebble(8);
		wBag.addPebble(9);

		// declare some lists
		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		// set the inital bag variables
		bBagInitial = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagInitial = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

		// replenish the black bag
        bBag.replenishPebbles();

		// set the result bag variables
		bBagResult = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagResult = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

		// the initial black bag should be empty
		assertEquals(new ArrayList<>(), bBagInitial);
		// The final black bag should be the same as the initial white bag
		assertEquals(wBagInitial, bBagResult);
		// the final white bag should be empty
		assertEquals(new ArrayList<>(), wBagResult);
		System.out.println("    Test passed");
    }

	/**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbleseEmptyFile() {
        System.out.println("Test replenishPebbles with an empty balck and white bag");
		System.out.println("    Both the black and white bags should remain empty");
		
		// empty the black bag
		bBag.savePebbles(new ArrayList<>(), bBag.STORAGE_FILE_LOCATION);

		// declare some lists
		List<Integer> bBagInitial, wBagInitial, bBagResult, wBagResult;

		// set the inital bag variables
		bBagInitial = bBag.loadPebbles(bBag.STORAGE_FILE_LOCATION);
		wBagInitial = wBag.loadPebbles(wBag.STORAGE_FILE_LOCATION);

		// replenish the black bag
        bBag.replenishPebbles();

		// set the result bag variables
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
		System.out.println("    Test passed");
    }

    /**
     * Test of getTotalNumPebbles method, of class BlackBag.
     */
    @Test
    public void testGetTotalNumPebbles() {
        System.out.println("Test getTotalNumPebbles on a file containing 25 pebbles");
		System.out.println("    expected output = 25");

        
        int expResult = 25;
		// save the output of the function in result,
        int result = bBag.getTotalNumPebbles();
        assertEquals(expResult, result);
		System.out.println("    Test passed");
    }
    
}
