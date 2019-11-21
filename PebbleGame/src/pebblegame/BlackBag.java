package pebblegame;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BlackBag extends FileHelpers
{
    final String STORAGE_FILE_LOCATION;
    char bagLetter;
	public Random random;

    public BlackBag(int numPlayers, String rangeFileName, int bagNum, Random random){	
		// Works out the letter for the black bag
		this.random = random;
        if (bagNum == 0){
            bagLetter = 'X';
        } else if (bagNum == 1){
            bagLetter = 'Y';
        } else if (bagNum == 2) {
            bagLetter = 'Z';
        }

		// Initialises some lists
        List<Integer> weightRange = new ArrayList<>();
        List<Integer> blackBagPebbles = new ArrayList<>();

		// loads the file for pebble range
        weightRange = loadPebbles(rangeFileName);

		// sets the file location using the bag letter
        STORAGE_FILE_LOCATION = "bBag"+bagLetter+"_file.csv";
		emptyFile(STORAGE_FILE_LOCATION);

		// adds the correct number of pebbles to the black bag,
		// randomly chooses the pebbles from the range file
        for (int i =0; i <= 11*numPlayers -1; i++){
			blackBagPebbles.add(weightRange.get(random.nextInt(weightRange.size())));
        }

		// saves the black bag to the file
        savePebbles(blackBagPebbles, STORAGE_FILE_LOCATION);
    }
    
    public synchronized int takeRandomPebble(){   
		// if there are no pebbles in the black bag then replenish the black bag
		if (getTotalNumPebbles() <= 0){
			replenishPebbles();
		}
		// if there are still no pebbles in the black bag then skip code block and return -1
		if (getTotalNumPebbles() > 0){

			// load the bag into an arraylist 
			List<Integer> blackBagPebbles = loadPebbles(STORAGE_FILE_LOCATION); 

			// get a random number between 0 and the size of the bag
			int rnd = random.nextInt(blackBagPebbles.size());

			// get the weight from the black bag where the index matches the random number, remove this from the array list
			int randomWeight = (int) blackBagPebbles.remove(rnd);

			// save the pebbles to the file
			savePebbles(blackBagPebbles, STORAGE_FILE_LOCATION);
			// return the random weight
			return randomWeight;
		} else {
			// if the bag could not be replenished then both white and black bags are empty
			System.out.println("another bag must be chosen");
			return -1;
		}

    }
    
    public synchronized void replenishPebbles(){
		// work out the white bag letter that is linked with the current black bag
		char wBagLetter = '_';

		if (bagLetter == 'X'){
			wBagLetter = 'A';
		} else if (bagLetter == 'Y'){
			wBagLetter = 'B';
		} else if (bagLetter == 'Z'){
			wBagLetter = 'C';
		} 

		// get the white bag file location
		String wBagFileLocation = "wBag" + wBagLetter + "_file.csv";

		// load the white bag into an array list
		List<Integer> pebbleArr = loadPebbles(wBagFileLocation);
		// save the contents of the white bag into the black bag file location
		savePebbles(pebbleArr, STORAGE_FILE_LOCATION);
		// empty the white bag
		emptyFile(wBagFileLocation);
    }
    
    public synchronized int getTotalNumPebbles() { 
		// load the black bag into an arraylist
		List<Integer> blackBagPebbles = loadPebbles(STORAGE_FILE_LOCATION); 
		// return the size of the arraylist as this will give the number of pebbles
        return blackBagPebbles.size();
    }
}
