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
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of drawFromBag method, of class Player.
     */
    @Test
    public void testDrawFromBag() {
        System.out.println("drawFromBag");
        BlackBag bBag = null;
        Player instance = null;
        instance.drawFromBag(bBag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePebble method, of class Player.
     */
    @Test
    public void testRemovePebble() {
        System.out.println("removePebble");
        int weight = 0;
        WhiteBag wBag = null;
        Player instance = null;
        instance.removePebble(weight, wBag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkWeight method, of class Player.
     */
    @Test
    public void testCheckWeight() {
        System.out.println("checkWeight");
        Player instance = null;
        Boolean expResult = null;
        Boolean result = instance.checkWeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
