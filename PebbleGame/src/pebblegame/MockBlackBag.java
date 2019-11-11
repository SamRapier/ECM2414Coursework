package pebblegame;

import java.util.List;


/**
 *
 * @author samra
 */
public class MockBlackBag extends FileHelpers implements BlackBagInterface
{
    // private String fileLocation;
    // private String[] pebbleArr;
    
    public MockBlackBag(int numPlayers, int bagNum){		
        super();
        fileName = "test/files/testFile4.csv";
    }
    
    @Override
    public int takeRandomPebble(){     
        List<Integer> pebbleArr = loadPebbles();   
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
        List<Integer> arr = loadPebbles();
        return arr.size();
    }
}
