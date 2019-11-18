package pebblegame;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samra
 */
public class FileHelpersTest {
    
    public FileHelpersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of loadPebbles method, of class FileHelpers.
     */
    @Test
    public void testLoadPebbles() {
        System.out.println("loadPebbles");
        
        // Test file contains 5,10,3,15
        String fileName = "test/files/testFile1.csv";        
        FileHelpers instance = new FileHelpers();
        List<Integer> arr = instance.loadPebbles(fileName);

        assertEquals(Arrays.asList(5,10,3,15), arr);       
    }
    
    
    /*
     * Test of loadPebbles method with an empty file
     */
    @Test
    public void testLoadPebblesEmpty(){
        System.out.println("loadPebbles with empty file");
        
        String fileName = "test/files/testFile2.csv";
        FileHelpers instance = new FileHelpers();
        List<Integer> arr = instance.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(), arr);
    }
    
    /*
     * Test of loadPebbles method with a multi line file
     */
    @Test
    public void testLoadPebblesMultiLine(){
        System.out.println("loadPebbles with multiple lines");
        
        String fileName = "test/files/testFile3.csv";
        FileHelpers instance = new FileHelpers();
        List<Integer> arr = instance.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(2,3,5,7,9,10), arr);
    }

    /**
     * Test of savePebbles method, of class FileHelpers.
     */
    @Test
    public void testSavePebbles() {
        System.out.println("savePebbles");
        String fileName = "test/files/testOutput1.csv";
        FileHelpers instance = new FileHelpers();
        
        List<Integer> testArray = new ArrayList<Integer>();
        testArray.add(2);
        testArray.add(1);
        testArray.add(4);
        testArray.add(6);

        List<Integer> arr = new ArrayList<>(testArray);
        
        // save to file
        instance.savePebbles(arr, fileName);
        
        // clear array and then load it from file
        arr.clear();
        arr = instance.loadPebbles(fileName);
        
        // if it matches are it has been emptied, it saved it correctly
        assertEquals(testArray, arr);
    }
    
    @Test
    public void testSavePebblesEmpty(){
        System.out.println("savePebbles");
        String fileName = "test/files/testOutput2.csv";
        FileHelpers instance = new FileHelpers();
         
        List<Integer> arr = new ArrayList<>();
        
        instance.savePebbles(arr, fileName);
        
        int result = instance.loadPebbles(fileName).size();
        
        assertEquals(0, result);       
    }
    
}