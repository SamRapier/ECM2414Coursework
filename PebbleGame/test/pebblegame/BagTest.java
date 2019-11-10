/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

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
public class BagTest {
    
    public BagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of loadPebbles method, of class Bag.
     */
    @Test
    public void testLoadPebbles() {
        System.out.println("loadPebbles");
        
        // Test file contains 5,10,3,15
        String fileName = "test/files/testFile1.csv";        
        Bag instance = new Bag();
        instance.loadPebbles(fileName);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(Arrays.asList(5,10,3,15), instance.pebbleArr);       
    }
    
    
    /*
     * Test of loadPebbles method with an empty file
     */
    @Test
    public void testLoadPebblesEmpty(){
        System.out.println("loadPebbles with empty file");
        
        String fileName = "test/files/testFile2.csv";
        Bag instance = new Bag();
        instance.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(), instance.pebbleArr);
    }
    
    /*
     * Test of loadPebbles method with a multi line file
     */
    @Test
    public void testLoadPebblesMultiLine(){
        System.out.println("loadPebbles with multiple lines");
        
        String fileName = "test/files/testFile3.csv";
        Bag instance = new Bag();
        instance.loadPebbles(fileName);
        
        assertEquals(Arrays.asList(2,3,5,7,9,10), instance.pebbleArr);
    }

    /**
     * Test of savePebbles method, of class Bag.
     */
    @Test
    public void testSavePebbles() {
        System.out.println("savePebbles");
        String fileName = "test/files/testOutput.csv";
        Bag instance = new Bag();
        
        List<Integer> testArray = new ArrayList<Integer>();
        testArray.add(2);
        testArray.add(1);
        testArray.add(4);
        testArray.add(6);
        
        // save to file
        instance.pebbleArr = testArray;
        instance.savePebbles(fileName);
        
        // clear array and then load it from file
        instance.pebbleArr.clear();
        instance.loadPebbles(fileName);
        
        // if it matches are it has been emptied, it saved it correctly
        assertEquals(testArray, instance.pebbleArr);
    }
    
}
