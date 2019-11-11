package pebblegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samra
 */
public class MockWhiteBag extends FileHelpers implements WhiteBagInterface{
	// private String fileLocation;
	// private String[] pebbleArr;

	public MockWhiteBag(int bagNum){
            fileName = "test/files/testFile5.csv";
	}

    @Override
	public void addPebble(int newPebbleWeight) {
		List<Integer> pebbleArr = loadPebbles();
		pebbleArr.add(newPebbleWeight);
		savePebbles(pebbleArr);
	}

    @Override
	public void removeAllPebbles() {
		// remove all from pebbleArr
		savePebbles(new ArrayList<Integer>());
	}
}
