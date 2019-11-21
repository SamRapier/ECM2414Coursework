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
    
    public FileHelpers(){}    
    
    public synchronized List loadPebbles(String fileLocation){
		// initialise the variables 
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

					// the the value is not a positive number, throw an exception
					if (intVal < 0){
						throw new IllegalArgumentException("All values must be positive integers");
					}
					// add the value to the array
                    pebbleArr.add(intVal);
                }
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
			// if the file isnt found throw exceptions
            System.err.println(e);
			System.err.println("Could not find specified file");
        } catch (IllegalArgumentException e){
			System.err.println(e);
		} 	

		// return the pebble array
        return pebbleArr;
    }
    
    public synchronized void savePebbles(List<Integer> arr, String fileLocation) {	
        String str = "";

        if (arr.size() > 0){
			// read through the array and add each number to the string with a comma and space after it
            for (int i = 0; i < arr.size() -1; i++){
                    str += arr.get(i) + ", ";
            }
			// for the last item in the array, add the number only to the string so that the string does not have a comma at the end.
            str += arr.get(arr.size() - 1);
        }

		// write the string the the file location that was provided
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }

    }

	public synchronized void emptyFile(String fileLocation){
		String str = "";

		// write an empty string the the file location, this will overwrite anything in the file and empty it
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }
	}

    public synchronized void writeDrawToFile(String fileOutputLocation, String fileStorageLocation, int adjustVar, BlackBag bBag, int playerNum) {
        // load the pebbles from the file
		List<Integer> arr = loadPebbles(fileStorageLocation);

		// make a string out of all data, this will be output to the file
		String str = "player" + playerNum + " has drawn a " + adjustVar + " from bag " + bBag.bagLetter 
			+ "\n" + "player" + playerNum + " hand is " + arr.toString() + "\n";

		// append the string to the file output location
		appendToAfile(fileOutputLocation, str);
    }

	public synchronized void writeDiscardToFile(String fileOutputLocation, String fileStorageLocation, int adjustVar, WhiteBag wBag, int playerNum) {
        // load the pebbles from the file
		List<Integer> arr = loadPebbles(fileStorageLocation);
		
		// make a string out of all data, this will be output to the file
		String str = "player" + playerNum + " has discarded a " + adjustVar + " to bag " + wBag.bagLetter
			+ "\n" + "player" + playerNum + " hand is " + arr.toString() + "\n";

		// append the string to the file output location
		appendToAfile(fileOutputLocation, str);
    }

	public synchronized void appendToAfile(String fileLocation, String str){
		// load all the file writers and buffered writers
		try (FileWriter fw = new FileWriter(fileLocation, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw))
		{
			// println to the print writer which allows us to append to files
			out.println(str);

        } catch (IOException e){
            System.err.println(e);

        }
	}
}
