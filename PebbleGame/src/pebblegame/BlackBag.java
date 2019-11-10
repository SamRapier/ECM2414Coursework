/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author samra
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
       
    public int takeRandomPebble(){
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
