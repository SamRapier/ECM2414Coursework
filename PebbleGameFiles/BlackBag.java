 import java.util.Scanner;
 import java.util.Random;
/**
 * Write a description of class BlackBag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackBag extends Bag
{
    private int bagNum;
    private String fileLocation;
	// private String[] pebbleArr;
    
    public BlackBag(int numPlayers, int bagNum){
        this.bagNum = bagNum;
		
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter location of bag number to %d load: ", bagNum);
        fileLocation = sc.nextLine();
		sc.close();

		loadPebbles(fileLocation);
        
    }
       
    public int takeRandPebble(){
		Random random = new Random();
		int rnd = random.nextInt(pebbleArr.size());
		int randomWeight = pebbleArr.get(rnd);

		pebbleArr.remove(rnd);
		return randomWeight;
	}
       
    
    public void replenishPebbles(){
		
	}
    
    
    public int getTotalNumPebbles(){ return pebbleArr.size(); }
}
