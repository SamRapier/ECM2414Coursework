 import java.util.Scanner;
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
        this.numPebbles = numPlayers * 11;
        this.bagNum = bagNum;
		
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter location of bag number to %d load: ", bagNum);
        fileLocation = scan.nextLine();
		sc.close();

		loadPebbles(fileLocation);
        
    }
       
    public void removePebble(int weight){
		String[] tmpArr = new String[pebbleArr.length - 1];
		int count = 0;

		for (int i = 0; i < pebbleArr.length; i++){			
			if(pebbleArr[i] != String.parseString(weight)){
				tmpArr[count] = pebbleArr[i];
				count++;
			}
		}
		pebbleArr = tmpArr;
	}
       
    
    public void replenishPebbles(){
		
	}
    
    
    public int getTotalNumPebbles(){ return pebbleArr.length; }
}
