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

/**
 *
 * @author samra
 */
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
            numPlayers = Integer.parseInt(input.readLine());

			// loop 3 times for 3 bags of each type
            for (int i = 0; i < 3; i++){
                System.out.print("Enter location of bag number to " + i + " load: ");
                String fileName = input.readLine();

				// exit the program is user entered an 'E'
				if (fileName.toUpperCase() == "E"){
                                    System.exit(0);
                                }

				// contruct the objects for each of the bags
                bBags[i] = new BlackBag(numPlayers, fileName, i);
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


    public static class Player extends FileHelpers implements Runnable{
        // 
        final String FILE_STORAGE_LOCATION, FILE_OUTPUT_LOCATION;
        public int playerNum;
        BlackBag[] bBags;
        WhiteBag[] wBags;
		AtomicBoolean doneFlag;
		int drawBag;
		

        public Player(int chosenBag, int playerNum, BlackBag[] _bBags, WhiteBag[] _wBags, AtomicBoolean _doneFlag){
            this.playerNum = playerNum;
            bBags = _bBags;
            wBags = _wBags;
			doneFlag = _doneFlag;
			drawBag = chosenBag;

            FILE_OUTPUT_LOCATION = "player" + this.playerNum + "_output.txt";
            FILE_STORAGE_LOCATION = "player" + this.playerNum + "Pebbles.csv";
			emptyFile(FILE_STORAGE_LOCATION);
			emptyFile(FILE_OUTPUT_LOCATION);


            for(int i = 0; i<10; i++){
                drawFromBag(bBags[chosenBag]);
            }
        }

        public void run(){
            Random rand = new Random();
            // check totalWeight of bag
            while (!checkWeight() && !doneFlag.get()) {
				// exit thread				
            
				// choose pebble to discrad
				// currently done at random
				List<Integer> pebbleArr = loadPebbles(FILE_STORAGE_LOCATION);
				int index = rand.nextInt(pebbleArr.size());
				int weight = pebbleArr.get(index);

				// discard from previous drawn bag
				discardPebble(weight, wBags[drawBag]);

				// write the player hand to output file
				// writeDiscardToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, weight, wBags[drawBag], playerNum);

				// Randomly choose a bag to draw from
				drawBag = rand.nextInt(3);

				// draw from bag
				while (!drawFromBag(bBags[drawBag])){
					drawBag = rand.nextInt(3);
				}

				// write the player hand to output file
				// writeDrawToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, adjustVar, bBags[drawBag], playerNum);
			}
			doneFlag.set(true);
			if (checkWeight()){
				System.out.println("player" + playerNum + " has WON!!!");
			}
        }

        // needs to be atomic
        public synchronized boolean drawFromBag(BlackBag bBag){
            List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);
            int newPebble = bBag.takeRandomPebble();
			if (newPebble != -1){
				playerPebbles.add(newPebble);
				savePebbles(playerPebbles, FILE_STORAGE_LOCATION);

				writeDrawToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, newPebble, bBag, playerNum);
				return true;
			} 
			return false;
        }

		// needs to be atomic
        public synchronized void discardPebble(int weight, WhiteBag wBag){
            // AtomicInteger pWeight;
            // pWeight.set(weight);
            List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);

            wBag.addPebble(weight);

            int weightIndex = playerPebbles.indexOf(weight);
            // AtomicInteger weightIndex;
            // weightIndex.set(playerPebbles.indexOf(weight));

            playerPebbles.remove(weightIndex);
            savePebbles(playerPebbles, FILE_STORAGE_LOCATION);

            writeDiscardToFile(FILE_OUTPUT_LOCATION, FILE_STORAGE_LOCATION, weight, wBag, playerNum);
        }
        
        public synchronized int getTotalWeight() {
            List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);
            int totalWeight = 0;
            
            for(int weight: playerPebbles){
                totalWeight += weight;
            }
            
            return totalWeight;
        }

        public synchronized Boolean checkWeight(){
            List<Integer> playerPebbles = loadPebbles(FILE_STORAGE_LOCATION);
            int totalWeight = 0;
            
            for(int weight: playerPebbles){
                totalWeight += weight;
            }
            
            if(totalWeight == 100){
				doneFlag.set(true);
                return true;
            }
            return false;
        }
    
    }
    
    
}
