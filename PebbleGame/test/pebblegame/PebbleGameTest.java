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

import jdk.jfr.Timestamp;
import pebblegame.PebbleGame.Player;

import static org.junit.Assert.*;

/**
 *
 * @author samra
 */
public class PebbleGameTest {
    
	public static BlackBag [] bBags;
	public static WhiteBag [] wBags;
	public static Player player;

    public PebbleGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
		bBags = new BlackBag[3];
		wBags = new WhiteBag[3];
		Random mockRandom = new MockRandom();
        AtomicBoolean doneFlag = new AtomicBoolean(false);
                
		for (int i = 0; i<3;i++){
			bBags[i] = new BlackBag(1, "file_2.csv", i, mockRandom);
			bBags[i].savePebbles(bBags[i].loadPebbles("file_2.csv"), bBags[i].STORAGE_FILE_LOCATION);
//			 bBags[i].random.setOverride(4);
			wBags[i] = new WhiteBag(i);
		}

		player = new Player(0, 1, bBags, wBags, doneFlag);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

//    /**
//     * Test of main method, of class PebbleGame.
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        // PebbleGame.main(args);
//
//		System.out.println(player.FILE_OUTPUT_LOCATION);
//    }

	@Test
	public void testDiscardPebble(){
		List<Integer> wBagInitial, wBagResult, playerInital, playerResult;
		wBagInitial = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);
		playerInital = player.loadPebbles(player.FILE_STORAGE_LOCATION);

		player.discardPebble(5, wBags[1]);
		playerResult = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		wBagResult = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);

                int result = wBagResult.get(0);
                
		assertEquals(new ArrayList<>(), wBagInitial);
		assertEquals(5, result);	
		assertEquals(playerResult.size(), playerInital.size() -1);
	}

	@Test
	public void testDiscardPebbleWhiteBagFilled(){
		wBags[1].addPebble(15);
		wBags[1].addPebble(4);
		wBags[1].addPebble(20);

		List<Integer> wBagInitial, wBagResult, playerInital, playerResult;
		wBagInitial = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);
		playerInital = player.loadPebbles(player.FILE_STORAGE_LOCATION);
                

		player.discardPebble(14, wBags[1]);


		playerResult = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		wBagResult = wBags[1].loadPebbles(wBags[1].STORAGE_FILE_LOCATION);

		int result = wBagResult.get(wBagResult.size() - 1);
                
		assertEquals(wBagInitial.size() + 1, wBagResult.size());
		assertEquals(14, result);	
		assertEquals(playerResult.size(), playerInital.size() -1);
	}

	@Test
	public void testDrawFromBag(){
		boolean resp = player.drawFromBag(bBags[1]);
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		int result = arr.get(arr.size() - 1);
		assertEquals(3, result);

	}	

	@Test
	public void testDrawFromBag1PebbleBlackBag(){
		List<Integer> pebble = new ArrayList<>();
                pebble.add(4);
		bBags[1].savePebbles(pebble, bBags[1].STORAGE_FILE_LOCATION);

		boolean resp = player.drawFromBag(bBags[1]);
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		int result = arr.get(arr.size() - 1);
		assertEquals(4, result);
	}

	@Test
	public void testDrawFromBagEmptyBlack(){
		bBags[1].savePebbles(new ArrayList<>(), bBags[1].STORAGE_FILE_LOCATION);
		
		List<Integer> addArr = new ArrayList<>();
		addArr.add(5);
		addArr.add(7);
		addArr.add(14);
		
		wBags[1].savePebbles(addArr, wBags[1].STORAGE_FILE_LOCATION);

		boolean resp = player.drawFromBag(bBags[1]);
		List<Integer> arr = player.loadPebbles(player.FILE_STORAGE_LOCATION);
		int result = arr.get(arr.size() - 1);
		assertEquals(5, result);
	}

	@Test
	public void testCheckWeightTrue(){
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,10));
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		assertTrue(player.checkWeight());
	}

	@Test
	public void testCheckWeightFalseTooBig(){
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,15,20,2,5,13,10));
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		assertFalse(player.checkWeight());
	}
	@Test
	public void testCheckWeightFalseTooSmall(){
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,15,2,2,5,1,1));
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		assertFalse(player.checkWeight());
	}
	@Test
	public void testCheckWeightFalse1Over(){
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,11));
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		assertFalse(player.checkWeight());
	}
	@Test
	public void testCheckWeightFalse1under(){
		List<Integer> playerArr = new ArrayList<>(Arrays.asList(12,14,23,6,4,11,2,5,13,9));
		player.savePebbles(playerArr, player.FILE_STORAGE_LOCATION);
		assertFalse(player.checkWeight());
	}    
}
