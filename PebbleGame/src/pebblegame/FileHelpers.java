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
public class FileHelpers
{
    String fileName;
    
    public FileHelpers(){}
    
    public FileHelpers(String fileName){
        this.fileName = fileName;
    }

    public List loadPebbles(){
        File file = new File(fileName);      
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
                    pebbleArr.add(Integer.parseInt(value));
                }
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            System.err.println(e);
        }

        return pebbleArr;
    }
    
    public void savePebbles(List<Integer> arr) {		
        String str = "";
        if (arr.size() > 0){
            for (int i = 0; i < arr.size() -1; i++){
                    str += arr.get(i) + ", ";
            }
            str += arr.get(arr.size() - 1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(str);
        } catch (IOException e){
            System.err.println(e);
        }

    }
}
