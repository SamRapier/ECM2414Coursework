/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

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
        String fileName = "";
        Bag instance = new Bag();
        instance.loadPebbles(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePebbles method, of class Bag.
     */
    @Test
    public void testSavePebbles() {
        System.out.println("savePebbles");
        String fileName = "";
        Bag instance = new Bag();
        instance.savePebbles(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
