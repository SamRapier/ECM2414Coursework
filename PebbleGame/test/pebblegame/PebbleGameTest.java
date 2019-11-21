package pebblegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import pebblegame.PebbleGame.Player;

import static org.junit.Assert.*;

public class PebbleGameTest {
    
	// decleare varaibles 
	public static BlackBag [] bBags;
	public static WhiteBag [] wBags;
	public static Player player;

    public PebbleGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
		// initialse varaiables 
		bBags = new BlackBag[3];
		wBags = new WhiteBag[3];
		Random mockRandom = new MockRandom();
        AtomicBoolean doneFlag = new AtomicBoolean(false);
                
		// loop 3 times to make the black and white bags
		for (int i = 0; i<3;i++){
			// use the mock random object instead of normal random so it is predictable
			bBags[i] = new BlackBag(1, "file_2.csv", i, mockRandom);
			// save file_2 pebbles to file to ensure we have all 25 pebbles instead of 11
			bBags[i].savePebbles(bBags[i].loadPebbles("file_2.csv"), bBags[i].STORAGE_FILE_LOCATION);

			wBags[i] = new WhiteBag(i);
		}

		// make a new player to test
		player = new Player(0, 1, bBags, wBags, doneFlag);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

	@Test
	public void testDiscardPebble(){
		System.out.println("Test discard pebbles with an empty white bag ");

		// declare arraylists for bags and players
		List<Integer> wBagInitial, wBagResult, playerInital, playerResult;

		// set the inital arraylists 
		wBagInitial = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);
		playerInital = player.loadPebbles(player.FILE_STORAGE_LOCATION);

		// discard a pebble from the player
		player.discardPebble(5, wBags[1]);

		// set the result arraylists
		playerResult = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		wBagResult = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);

		// get the first item in the white bag
		int result = wBagResult.get(0);
                
		
		// the white bag should have initially been empty
		assertEquals(new ArrayList<>(), wBagInitial);
		// the white bag should now contain the variable that was discarded
		assertEquals(5, result);	
		// the new player should have 1 less pebble
		assertEquals(playerResult.size(), playerInital.size() -1);
		System.out.println("    Test passed");
	}

	@Test
	public void testDiscardPebbleWhiteBagFilled(){
		System.out.println("Test discardPebbles with a white bag which already has pebbles");

		// add pebbles to the white bag
		wBags[1].addPebble(15);
		wBags[1].addPebble(4);
		wBags[1].addPebble(20);

		// declare arraylists for bags and players
		List<Integer> wBagInitial, wBagResult, playerInital, playerResult;
		// set the inital arraylists 
		wBagInitial = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);
		playerInital = player.loadPebbles(player.FILE_STORAGE_LOCATION);
                
		// discard a pebble from the player
		player.discardPebble(14, wBags[1]);

		// set the result arraylists
		playerResult = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		wBagResult = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);

		// get the last item in the white bag
		int result = wBagResult.get(wBagResult.size() - 1);
                
		// the size of the white bag should be 1 larger than it was initially
		assertEquals(wBagInitial.size() + 1, wBagResult.size());
		// the item discarded should be the same as the item added
		assertEquals(14, result);	
		// the players jamd of pebbles should be decreased by 1
		assertEquals(playerResult.size(), playerInital.size() -1);
		System.out.println("    Test passed");
	}

	@Test
	public void testDrawFromBag(){
		System.out.println("Test drawFromBag with a filled black bag");

		// draw a pebble from the black bag
		boolean resp = player.drawFromBag(bBags[1]);

		// load the pebbles into an arraylist
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);

		// get the last item in the array list
		int result = arr.get(arr.size() - 1);

		// the last item should be a 3 since the mock random object should pick the index
		assertEquals(3, result);
		System.out.println("    Test passed");
	}	

	@Test
	public void testDrawFromBag1PebbleBlackBag(){
		System.out.println("Test drawFromBag with a black bag which has only 1 pebble");

		// create a new arraylist with 1 item
		List<Integer> pebble = new ArrayList<>();
		pebble.add(4);
		// save array list to black bag file
		bBags[1].savePebbles(pebble, bBags[1].STORAGE_FILE_LOCATION);

		// draw a pebble from the black bag
		boolean resp = player.drawFromBag(bBags[1]);
		// load the players hand into an arraylist
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);

		// get the last item in the players hand
		int result = arr.get(arr.size() - 1);

		// the last item should be 4 since this was the item in the black bag
		assertEquals(4, result);
		System.out.println("    Test passed");
	}

	@Test
	public void testDrawFromBagEmptyBlack(){
		System.out.println("Test drawFromBag with an empty black bag and make it replenish from a white bag");

		// save an empty arraylist to the black bag file
		bBags[1].savePebbles(new ArrayList<>(), bBags[1].STORAGE_FILE_LOCATION);
		
		// make an array list and add some items to it
		// then save this arraylit to the white bag file
		List<Integer> addArr = new ArrayList<>();
		addArr.add(5);
		addArr.add(7);
		addArr.add(14);
		wBags[1].savePebbles(addArr, wBags[1].STORAGE_FILE_LOCATION);

		// draw a pebble from the black bag
		// this will replenish the black bag with the pebbles from the white bag
		boolean resp = player.drawFromBag(bBags[1]);

		// load the players hand into an arraylist
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);

		// get the last item in the players hand
		int result = arr.get(arr.size() - 1);

		// the last item in the players hand should be the first item in the white bag
		assertEquals(5, result);
		System.out.println("    Test passed");
	}

	@Test
	public void testCheckWeightTrue(){
		System.out.println("Test checkWeight on a players pebbles which sum to 100");

		// make a new arraylist with predefiend variables
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,10));
		// save the new array list to the player file
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		// run the check weight function, if it outputs true then it is correct as the total weight is 100
		assertTrue(player.checkWeight());
		System.out.println("    Test passed");
	}

	@Test
	public void testCheckWeightFalseTooBig(){
		System.out.println("Test checkWeight on a players pebbles which sum to greater than 100");
		// make a new arraylist with predefiend variables
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,15,20,2,5,13,10));
		// save the new array list to the player file
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		// run the check weight function, if it outputs false then it is correct as the total weight is not 100
		assertFalse(player.checkWeight());
		System.out.println("    Test passed");
	}
	@Test
	public void testCheckWeightFalseTooSmall(){
		System.out.println("Test checkWeight on a players pebbles which sum to less than 100");
		// make a new arraylist with predefiend variables
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,15,2,2,5,1,1));
		// save the new array list to the player file
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		// run the check weight function, if it outputs false then it is correct as the total weight is not 100
		assertFalse(player.checkWeight());
		System.out.println("    Test passed");
	}
	@Test
	public void testCheckWeightFalse1Over(){
		System.out.println("Test checkWeight on a players pebbles which sum to 101");
		// make a new arraylist with predefiend variables
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,11));
		// save the new array list to the player file
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		// run the check weight function, if it outputs false then it is correct as the total weight is not 100
		assertFalse(player.checkWeight());
		System.out.println("    Test passed");
	}
	@Test
	public void testCheckWeightFalse1under(){
		System.out.println("Test checkWeight on a players pebbles which sum to 99");
		// make a new arraylist with predefiend variables
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,9));
		// save the new array list to the player file
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		// run the check weight function, if it outputs false then it is correct as the total weight is not 100
		assertFalse(player.checkWeight());
	}    
}
