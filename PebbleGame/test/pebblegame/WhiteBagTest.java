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
public class WhiteBagTest {
    
    public WhiteBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addPebble method, of class WhiteBag.
     */
    @Test
    public void testAddPebble() {
        System.out.println("addPebble");
        int newPebbleWeight = 0;
        WhiteBag instance = null;
        instance.addPebble(newPebbleWeight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllPebbles method, of class WhiteBag.
     */
    @Test
    public void testRemoveAllPebbles() {
        System.out.println("removeAllPebbles");
        WhiteBag instance = null;
        instance.removeAllPebbles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
