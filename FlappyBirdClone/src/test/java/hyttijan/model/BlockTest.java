/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

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
    Block block2;
    public BlockTest() {
    }
    
    
    @Before
    public void setUp() {
        block = new Block(100);
        block2= new Block(-559);
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Testataan että updateX-metodi palauttaa oikean arvon.
     */
    @Test
    public void testUpdateX(){
        block2.move();
        assertEquals(block2.getX(),-560);
    }
    @Test
    public void testUpdateX2(){
        block2.move();
        block2.move();
        assertEquals(block2.getX(),640);
    }
    /**
     * Testataan että getX-metodi palauttaa oikean arvon.
     */
    @Test
    public void testGetX() {
        int expectedResult = 100;
        assertEquals(block.getX(),expectedResult);
    }
    /**
     * Testataan että generateY-metodi generoi palikoiden väliin tarpeeksi tilaa.
     */
    @Test
    public void testGenerateY() {
        for(int i=0;i<999;i++){
            block.generateY();
            assertTrue(block.getY()-block.getY2()==120);
        }
    }

 
    
}
