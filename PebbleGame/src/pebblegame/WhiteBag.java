package pebblegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WhiteBag extends FileHelpers{


	final String STORAGE_FILE_LOCATION;
	char bagLetter;

	public WhiteBag(int bagNum){
		// Works out the letter for the white bag
		if (bagNum == 0){
            bagLetter = 'A';
        } else if (bagNum == 1){
            bagLetter = 'B';
        } else if (bagNum == 2) {
            bagLetter = 'C';
		}
		// sets the file location using the bag letter
		STORAGE_FILE_LOCATION = "wBag" + bagLetter + "_file.csv";	
		emptyFile(STORAGE_FILE_LOCATION);	
	}

	// adds a pebble with a specified weight to the array
	public synchronized void addPebble(int newPebbleWeight){
		List<Integer> pebbleArr = loadPebbles(STORAGE_FILE_LOCATION);
		pebbleArr.add(newPebbleWeight);
		savePebbles(pebbleArr, STORAGE_FILE_LOCATION);
	}
}
