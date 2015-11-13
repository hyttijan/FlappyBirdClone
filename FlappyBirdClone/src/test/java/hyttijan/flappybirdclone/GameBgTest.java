/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author janne
 */
public class GameBgTest {
      GameBg gameBg;
    public GameBgTest() {
    }
    
   
    
    @Before
    public void setUp() {
       gameBg = new GameBg();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testataan updateX-metodia että x:n ja x2:sen arvot eivät ole koskaan pienempiä kuin -641.
     */
    @Test
    public void testUpdateX() {
        for(int i=0;i<640;i++){        
        gameBg.updateX();
        assertFalse(gameBg.getX()<=-641);
        assertFalse(gameBg.getX2()<=-641);
        }
    }

    
    
}
