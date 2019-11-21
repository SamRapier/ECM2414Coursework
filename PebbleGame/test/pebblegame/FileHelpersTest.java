package pebblegame;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FileHelpersTest {
    
	private static FileHelpers fileHelper;

    public FileHelpersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
		fileHelper = new FileHelpers();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of loadPebbles method, of class FileHelpers.
     */
    @Test
    public void testLoadPebbles() {
        System.out.println("Test loadPebbles with normal data");
        
        // Test file contains 5,10,3,15
        String fileName = "test/files/testFile1.csv";        
        List<Integer> arr = fileHelper.loadPebbles(fileName);

        assertEquals(Arrays.asList(5,10,3,15), arr);      
		System.out.println("    Test passed"); 
    }
    
    
    /*
     * Test of loadPebbles method with an empty file
     */
    @Test
    public void testLoadPebblesEmpty(){
        System.out.println("Test loadPebbles with empty file");
        
        String fileName = "test/files/testFile2.csv";    
        List<Integer> arr = fileHelper.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(), arr);
		System.out.println("    Test passed");
    }
    
    /*
     * Test of loadPebbles method with a multi line file
     */
    @Test
    public void testLoadPebblesMultiLine(){
        System.out.println("Test loadPebbles with multiple lines");
        
        String fileName = "test/files/testFile3.csv";
        List<Integer> arr = fileHelper.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(2,3,5,7,9,10), arr);
		System.out.println("    Test passed");
    }

    /**
     * Test of savePebbles method, of class FileHelpers.
     */
    @Test
    public void testSavePebbles() {
        System.out.println("Test savePebbles");
        String fileName = "test/files/testOutput1.csv";
        
        // adds integers to the array list
        List<Integer> testArray = new ArrayList<Integer>();
        testArray.add(2);
        testArray.add(1);
        testArray.add(4);
        testArray.add(6);

        List<Integer> arr = new ArrayList<>(testArray);
        
        // save to file
        fileHelper.savePebbles(arr, fileName);
        
        // clear array and then load it from file
        arr.clear();
        arr = fileHelper.loadPebbles(fileName);
        
        // if it matches are it has been emptied, it saved it correctly
        assertEquals(testArray, arr);
		System.out.println("    Test passed");
    }
    
    @Test
    public void testSavePebblesEmpty(){
        // saves an empty array, if the array it then loads is empty then
        // the test clears, if not an assertion will be raised
        System.out.println("Test savePebbles with an empty arraylist");
        String fileName = "test/files/testOutput2.csv";
         
        List<Integer> arr = new ArrayList<>();
        
        fileHelper.savePebbles(arr, fileName);
        
        int result = fileHelper.loadPebbles(fileName).size();
        
        assertEquals(0, result);   
		System.out.println("    Test passed");    
    }
    
}
