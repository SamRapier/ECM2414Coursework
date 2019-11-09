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
public class BlackBagTest {
    
    public BlackBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of takeRandPebble method, of class BlackBag.
     */
    @Test
    public void testTakeRandPebble() {
        System.out.println("takeRandPebble");
        BlackBag instance = null;
        int expResult = 0;
        int result = instance.takeRandPebble();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replenishPebbles method, of class BlackBag.
     */
    @Test
    public void testReplenishPebbles() {
        System.out.println("replenishPebbles");
        BlackBag instance = null;
        instance.replenishPebbles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalNumPebbles method, of class BlackBag.
     */
    @Test
    public void testGetTotalNumPebbles() {
        System.out.println("getTotalNumPebbles");
        BlackBag instance = null;
        int expResult = 0;
        int result = instance.getTotalNumPebbles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
