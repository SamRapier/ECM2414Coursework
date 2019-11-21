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
    
    // @Override
    public synchronized int takeRandomPebble(){   
		if (getTotalNumPebbles() <= 0){
			replenishPebbles();
		}
		if (getTotalNumPebbles() > 0){

			List<Integer> blackBagPebbles = loadPebbles(STORAGE_FILE_LOCATION); 

			int rnd = random.nextInt(blackBagPebbles.size());

			int randomWeight = (int) blackBagPebbles.remove(rnd);

			savePebbles(blackBagPebbles, STORAGE_FILE_LOCATION);
			return randomWeight;
		} else {
			System.out.println("another bag must be chosen");
			return -1;
		}

    }
    
    public synchronized void replenishPebbles(){
		char wBagLetter = '_';

		if (bagLetter == 'X'){
			wBagLetter = 'A';
		} else if (bagLetter == 'Y'){
			wBagLetter = 'B';
		} else if (bagLetter == 'Z'){
			wBagLetter = 'C';
		} 

		String wBagFileLocation = "wBag" + wBagLetter + "_file.csv";

		List<Integer> pebbleArr = loadPebbles(wBagFileLocation);
		savePebbles(pebbleArr, STORAGE_FILE_LOCATION);
		emptyFile(wBagFileLocation);
    }
    
    public synchronized int getTotalNumPebbles() {        
		List<Integer> blackBagPebbles = loadPebbles(STORAGE_FILE_LOCATION); 
        return blackBagPebbles.size();
    }
}
