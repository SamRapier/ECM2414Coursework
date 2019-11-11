package pebblegame;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author samra
 */
public class BlackBag extends FileHelpers implements BlackBagInterface
{
    // private String fileLocation;
    // private String[] pebbleArr;

    public BlackBag(int numPlayers, int bagNum){		
        super();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.printf("Enter location of bag number to %d load: ", bagNum);
            fileName = sc.nextLine();

        } catch (Exception e){
            System.err.println(e);
        }
    }
    
    @Override
    public int takeRandomPebble(){    
        List<Integer> pebbleArr = loadPebbles();
        Random random = new Random();

        int rnd = random.nextInt(pebbleArr.size());
        int randomWeight = (int) pebbleArr.remove(rnd);

        savePebbles(pebbleArr);
        return randomWeight;
    }
    
    @Override
    public void replenishPebbles(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getTotalNumPebbles() {
        List<Integer> arr = loadPebbles();
        return arr.size();
    }
}
