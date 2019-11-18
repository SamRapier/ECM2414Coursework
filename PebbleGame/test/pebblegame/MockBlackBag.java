package pebblegame;

import java.util.List;
import pebblegame.BlackBagInterface;
import pebblegame.FileHelpers;


/**
 *
 * @author samra
 */
public class MockBlackBag extends FileHelpers implements BlackBagInterface
{
    // private String fileLocation;
    // private String[] pebbleArr;
    final String STORAGE_FILE_LOCATION;
	char bagLetter;
    
    public MockBlackBag(int numPlayers, String rangeFileName, int bagNum){
		if (bagNum == 0){
            bagLetter = 'X';
        } else if (bagNum == 1){
            bagLetter = 'Y';
        } else if (bagNum == 2) {
            bagLetter = 'Z';
        }

		List<Integer> weightRange = new ArrayList<>();
        List<Integer> blackBagPebbles = new ArrayList<>();

        weightRange = loadPebbles(rangeFileName);
        STORAGE_FILE_LOCATION = "test/files/test_bBag"+bagLetter+"_file.csv";
		emptyFile(STORAGE_FILE_LOCATION);

		for (int i =0; i <= 11*numPlayers -1; i++){
			blackBagPebbles.add(weightRange.get(i));
        }

		savePebbles(blackBagPebbles, STORAGE_FILE_LOCATION);
    }
    
    @Override
    public int takeRandomPebble(){  
		if (getTotalNumPebbles() <= 0){
			replenishPebbles();
		}   

		if (getTotalNumPebbles() > 0){

			List<Integer> blackBagPebbles = loadPebbles(STORAGE_FILE_LOCATION); 

			// We will choose the 4th item in the file as our weight to test
			int randomWeight = (int) blackBagPebbles.remove(4);

			// we won't save the file in the mock object since we want to 
			// reuse it in our unit tests
			// savePebbles(blackBagPebbles, STORAGE_FILE_LOCATION);
			return randomWeight;
		} else {
			System.out.println("another bag must be chosen");
			return -1;
		}


        List<Integer> pebbleArr = loadPebbles(fileName);   
        int rnd = 4;
        int randomWeight = (int) pebbleArr.remove(rnd);

        // We dont want to save the file in the mock object so we 
        // can run the test again in the future
        // savePebbles();

        return randomWeight;
    }
    
    @Override
    public void replenishPebbles(){
	    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalNumPebbles(){
        List<Integer> arr = loadPebbles(fileName);
        return arr.size();
    }
}
