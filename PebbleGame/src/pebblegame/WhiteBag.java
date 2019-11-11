package pebblegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samra
 */
public class WhiteBag extends FileHelpers implements WhiteBagInterface{
	// private String fileLocation;
	// private String[] pebbleArr;

	public WhiteBag(int bagNum){
            Scanner sc = new Scanner(System.in);
            System.out.printf("Enter location of bag number %d to load: ", bagNum);
            fileName = sc.nextLine();
            sc.close();
	}

	@Override
	public void addPebble(int newPebbleWeight){
		List<Integer> pebbleArr = loadPebbles();
		pebbleArr.add(newPebbleWeight);
		savePebbles(pebbleArr);
	}

	@Override
	public void removeAllPebbles(){
		// remove all from pebbleArr
		List<Integer> pebbleArr = new ArrayList<Integer>();
		savePebbles(pebbleArr);
	}

	

}
