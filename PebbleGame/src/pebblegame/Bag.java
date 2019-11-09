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
import java.util.ArrayList;
import java.util.Arrays;
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
		// pebbleArr = new String[0];
    }

    public void loadPebbles(String fileName){
        File file = new File(fileName);      
        List<String> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                lines = Arrays.asList(values);
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		for (String value: lines) {
            pebbleArr.add(Integer.parseInt(value));                
		}
    }
    
    public void savePebbles(String fileName) {
		try {		
			String str = "";
			for (int i = 0; i < pebbleArr.size(); i++){
				str += pebbleArr.get(i) + ", ";
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			writer.write(str);		
			writer.close();
		} catch (Exception e){}
	}
}
