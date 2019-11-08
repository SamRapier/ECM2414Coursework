import java.util.Scanner;
public class WhiteBag extends Bag{
	private String fileLocation;
	// private String[] pebbleArr;

	public WhiteBag(int bagNum){
		Scanner sc = new Scanner(System.in);
        System.out.printf("Enter location of bag number %d to load: ", bagNum);
        fileLocation = sc.nextLine();
		sc.close();
	}

	public void addPebble(int newPebbleWeight){
		pebbleArr.add(newPebbleWeight);
		savePebbles(fileLocation);
	}

	public void removeAllPebbles(){
		// remove all from pebbleArr
		savePebbles(fileLocation);
	}

	

}