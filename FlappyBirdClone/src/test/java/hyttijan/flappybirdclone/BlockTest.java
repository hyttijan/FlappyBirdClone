/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janne
 */
public class BlockTest {
    Block block;
    public BlockTest() {
    }
    
    
    @Before
    public void setUp() {
        block = new Block(100);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testataan että getX-metodi palauttaa oikean arvon.
     */
    @Test
    public void testGetX() {
        int expectedResult = 100;
        assertEquals(block.getX(),expectedResult);
    }

 
    
}
