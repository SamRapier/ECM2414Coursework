/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.util.Scanner;

/**
 *
 * @author samra
 */
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
