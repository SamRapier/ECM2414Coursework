package pebblegame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pebblegame.PebbleGame.Player;

/**
 *
 * @author samra
 */
public class FileHelpers
{
    // String fileName;
    
    public FileHelpers(){}    
    
    public synchronized List loadPebbles(String fileLocation){
        File file = new File(fileLocation);      
        Scanner inputStream;
        List<Integer> pebbleArr = new ArrayList<>();       

        try{
            inputStream = new Scanner(file);

            // empty the array list
            pebbleArr.clear();
            
            // loop through the file, store each line in an array list
            while(inputStream.hasNext()){
                String line = inputStream.next();
                // splits the line into an array of the values
                String[] values = line.split(",");
                
                // stores the file in an integer array list
                for (String value : values){
					int intVal = Integer.parseInt(value);
					if (intVal < 0){
						throw new IllegalArgumentException("All values must be positive integers");
					}
                    pebbleArr.add(Integer.parseInt(value));
                }
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
			System.err.println("Could not find specified file");
        } catch (IllegalArgumentException e){
			System.err.println(e);
		} 	

        return pebbleArr;
    }
    
    public synchronized void savePebbles(List<Integer> arr, String fileLocation) {		
        String str = "";
        if (arr.size() > 0){
            for (int i = 0; i < arr.size() -1; i++){
                    str += arr.get(i) + ", ";
            }
            str += arr.get(arr.size() - 1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }

    }

	public synchronized void emptyFile(String fileLocation){
		String str = "";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }
	}

    public synchronized void writeDrawToFile(String fileOutputLocation, String fileStorageLocation, int adjustVar, BlackBag bBag, int playerNum) {
        List<Integer> arr = loadPebbles(fileStorageLocation);

		String str = "player" + playerNum + " has drawn a " + adjustVar + " from bag " + bBag.bagLetter 
			+ "\n" + "player" + playerNum + " hand is " + arr.toString() + "\n";

		// System.out.println("player" + playerNum + " has drawn a " + adjustVar + " from bag " + bBag.bagLetter);
        // System.out.println("player" + playerNum + " hand is " + arr.toString());

		appendToAfile(fileOutputLocation, str);
    }

	public synchronized void writeDiscardToFile(String fileOutputLocation, String fileStorageLocation, int adjustVar, WhiteBag wBag, int playerNum) {
        List<Integer> arr = loadPebbles(fileStorageLocation);
		
		String str = "player" + playerNum + " has discarded a " + adjustVar + " to bag " + wBag.bagLetter
			+ "\n" + "player" + playerNum + " hand is " + arr.toString() + "\n";

		// System.out.println("player" + playerNum + " has discarded a " + adjustVar + " to bag " + wBag.bagLetter);
        // System.out.println("player" + playerNum + " hand is " + arr.toString());

		appendToAfile(fileOutputLocation, str);
    }

	public synchronized void appendToAfile(String fileLocation, String str){
		// try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
		try (FileWriter fw = new FileWriter(fileLocation, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw))
		{
			out.println(str);
            // writer.append(str);
        } catch (IOException e){
            System.err.println(e);
        }
	}
}
