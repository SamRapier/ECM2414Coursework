import java.io.*;
import java.util.*;
/**
 * Write a description of class Bag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Bag
{
	String[] pebbleArr;
	
    public Bag()
    {
		// pebbleArr = new String[0];
    }

    public loadPebbles(String fileName){
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

        pebbleArr = new String[lines.size()];
		int columnNo = 0;
		for (String value: lines) {
			pebbleArr[columnNo] = value;
			columnNo++;                
		}
    }
    
    public void savePebbles(String fileName) {
		try {		
			String str = "";
			for (int i = 0; i < pebbleArr.length; i++){
				str += pebbleArr[i] + ", ";
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			writer.write(str);		
			writer.close();
		} catch (Exception e){}
	}
}

