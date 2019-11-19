package pebblegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pebblegame.FileHelpers;
import pebblegame.WhiteBagInterface;

/**
 *
 * @author samra
 */
public class MockWhiteBag extends FileHelpers implements WhiteBagInterface{
	
	final String STORAGE_FILE_LOCATION;
	char bagLetter;

	public MockWhiteBag(int bagNum){
		if (bagNum == 0){
            bagLetter = 'A';
        } else if (bagNum == 1){
            bagLetter = 'B';
        } else if (bagNum == 2) {
            bagLetter = 'C';
        }
		STORAGE_FILE_LOCATION = "test/files/test_wBag" + bagLetter + "_file.csv";	
		emptyFile(STORAGE_FILE_LOCATION);	
	}

    @Override
	public void addPebble(int newPebbleWeight) {
		List<Integer> pebbleArr = loadPebbles(STORAGE_FILE_LOCATION);
		pebbleArr.add(newPebbleWeight);
		savePebbles(pebbleArr, STORAGE_FILE_LOCATION);
	}
}
