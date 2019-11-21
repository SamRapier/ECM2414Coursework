package pebblegame;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class PebbleGame {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {     
		
		// Make arrays of each of the Bag objects
		BlackBag[] bBags = new BlackBag[3];
		WhiteBag[] wBags = new WhiteBag[3];

		// initialise numPlayers and doneFlag
		int numPlayers = 0;
		AtomicBoolean doneFlag = new AtomicBoolean(false);

		// get input for numPlayers and file locations here
		try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))){
			System.out.print("How many players are in the game: ");
			// Take the input, check if the user wants to exit
			String strNumPlayers = input.readLine();
			checkForExit(strNumPlayers);
			// parse the input into an integer variable
			numPlayers = Integer.parseInt(strNumPlayers);

			// loop 3 times for 3 bags of each type
			for (int i = 0; i < 3; i++){
				System.out.print("Enter location of black bag " + i + " to load: ");
				// take the input from the user
				String fileName = input.readLine();
				// check if the user want to exit
				checkForExit(fileName);

				// contruct the objects for each of the bags
				bBags[i] = new BlackBag(numPlayers, fileName, i, new Random());
				wBags[i] = new WhiteBag(i);
			}

		} catch (Exception e){
			System.err.println(e);
		}

		Random rand = new Random();
		// loop for each of the players create a new player and load the pebbles from a randomly
		// chosen bag
		for (int i = 0; i < numPlayers; i++){
			int chosenBag = rand.nextInt(3);

			// create the thread object and start it
			Thread object = new Thread(new Player(chosenBag, i, bBags, wBags, doneFlag));
			object.start();
		}

	}

	public static void checkForExit(String str){
		// if the str is equal to E, exit the program
		if (str.toUpperCase().equals("E")){
			System.exit(0);
		}
	}


	public static class Player extends FileHelpers implements Runnable{
		// declare vairables
		final String FILE_STORAGE_LOCATION, FILE_OUTPUT_LOCATION;
		public int playerNum;
		BlackBag[] bBags;
		WhiteBag[] wBags;
		AtomicBoolean doneFlag;
		int drawBag;
		

		public Player(int chosenBag, int playerNum, BlackBag[] _bBags, WhiteBag[] _wBags, AtomicBoolean _doneFlag){
			// initialise vairables
			this.playerNum = playerNum;
			bBags = _bBags;
			wBags = _wBags;
			doneFlag = _doneFlag;
			drawBag = chosenBag;

			FILE_OUTPUT_LOCATION = "player" + this.playerNum + "_output.txt";
			FILE_STORAGE_LOCATION = "player" + this.playerNum + "Pebbles.csv";

			// make sure to empty the files to ensure that no data from the previous simulation causes any issues
			emptyFile(FILE_STORAGE_LOCATION);
			emptyFile(FILE_OUTPUT_LOCATION);

			// each player needs to start with 10 pebbles, this loops through 10 times and draws from the chosen black bag
			for(int i = 0; i<10; i++){
				drawFromBag(bBags[chosenBag]);
			}
		}

		public void run(){
			Random rand = new Random();
			// check totalWeight of bag and the doneFlag
			// if the doneFlag is set then one of the threads has completed and reached a total weight of 100
			while (!checkWeight() && !doneFlag.get()) {		
			
				// choose pebble to discrad
				// currently done at random
				List<Integer> pebbleArr = loadPebbles(FILE_STORAGE_LOCATION);
				int index = rand.nextInt(pebbleArr.size());
				int weight = pebbleArr.get(index);

				// discard from previous drawn bag
				discardPebble(weight, wBags[drawBag]);

				// Randomly choose a bag to draw from
				drawBag = rand.nextInt(3);

				// draw from bag, if pebbles cannot be drawn from the bag since both the black bag and 
				// corresponding white bag are empty, choose a another bag to draw from
				while (!drawFromBag(bBags[drawBag])){
					drawBag = rand.nextInt(3);
				}				
			}
			// set the doneFlag to true
			doneFlag.set(true);
			// print out which player has won
			if (checkWeight()){
				System.out.println("player" + playerNum + " has WON!!!");
			}
		}

		public synchronized boolean drawFromBag(BlackBag bBag){
			// load the pebbles into an arraylist from the file
			List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);

			// take a random pebble from the black bag
			int newPebble = bBag.takeRandomPebble();

			// if newPebble is -1, then the black bag is empty and cannot be replenished
			if (newPebble != -1){
				// add the new pebble to the array list
				playerPebbles.add(newPebble);
				// save the array list to the file
				savePebbles(playerPebbles, FILE_STORAGE_LOCATION);

				// write the output stream to the file
				writeDrawToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, newPebble, bBag, playerNum);
				
				// return true since a pebble was drawn
				return true;
			} 
			// return false since the pebble could not be retrieved
			return false;
		}

		public synchronized void discardPebble(int weight, WhiteBag wBag){
			// load the pebbles from the file into an arryalist
			List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);

			// add the weigth provided into the white bag
			wBag.addPebble(weight);

			// get the index of the weight in the pebble array
			int weightIndex = playerPebbles.indexOf(weight);

			// remove the variable at that point in the index
			playerPebbles.remove(weightIndex);

			// save the pebble array to the file
			savePebbles(playerPebbles, FILE_STORAGE_LOCATION);

			// write the output stream to file
			writeDiscardToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, weight, wBag, playerNum);
		}
		
		public synchronized Boolean checkWeight(){
			// load the pebbles from the file into an arraylist
			List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);
			// initialise totalWeight
			int totalWeight = 0;
			
			// for each pebble in the array, add the weight to the total weight
			for(int weight: playerPebbles){
				totalWeight += weight;
			}
			
			// if the total weight is equal to 100, return true and set the doneFlag to true
			if(totalWeight == 100){
				doneFlag.set(true);
				return true;
			}
			return false;
		}
	
	}
}
