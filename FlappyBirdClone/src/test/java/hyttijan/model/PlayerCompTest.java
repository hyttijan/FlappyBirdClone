/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hyttijan
 */
public class PlayerCompTest {
    PlayerComp playerComp;
    Player player1;
    Player player2;
    Player player3;
    public PlayerCompTest() {
    
    }
    
   
    
    @Before
    public void setUp() {
    playerComp = new PlayerComp();
    player1 = new Player("Test",1);
    player2 = new Player("Test2",2);
    player3 = new Player("Test",2);
    }
    
    /**
     * Test of compare method, of class PlayerComp.
     */
    @Test
    public void testCompare() {
     int expResult = 0;
     int result =playerComp.compare(player3, player2);
     assertEquals(expResult,result);
  
    }
    @Test
    public void testCompare2() {
     int expResult = 1;
     int result =playerComp.compare(player1, player2);
     assertEquals(expResult,result);
  
    }
     @Test
    public void testCompare3() {
     int expResult = -1;
     int result =playerComp.compare(player2, player1);
     assertEquals(expResult,result);
  
    }
    
}
