/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samra
 */
public class Bag
{
    List<Integer> pebbleArr = new ArrayList<>();
	//String[] pebbleArr;
	
    public Bag()
    {
    }

    public void loadPebbles(String fileName){
        File file = new File(fileName);      
        Scanner inputStream;       

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
                    pebbleArr.add(Integer.parseInt(value));
                }
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    public void savePebbles(String fileName) {		
        String str = "";
        for (int i = 0; i < pebbleArr.size(); i++){
                str += pebbleArr.get(i) + ", ";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }

    }
}
