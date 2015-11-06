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
public class BirdTest {
    Bird bird;
    public BirdTest() {
    }
    
    
    
    @Before
    public void setUp() {
       bird = new Bird(290,220); 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testataan setY-metodia.
     */
    @Test
    public void testSetY() {
        double expectedResult = 100;
        bird.setY(100);
       assertEquals(bird.getY(),expectedResult,0);
    }

    /**
     * Testataan getX-metodia.
     */
    @Test
    public void testGetX() {
        double expectedResult = 290;
        assertEquals(bird.getX(),expectedResult,0);
    }

    /**
     * Testataan getWidth-metodia.
     */
    @Test
    public void testGetWidth() {
        int expectedResult = 44;
        assertEquals(bird.getWidth(),expectedResult);
    }

    /**
     * Testataan getHeight-metodia.
     */
    @Test
    public void testGetHeight() {
       
     assertEquals(bird.getHeight(),36);
    }

    /**
     * Testataan getY-metodia.
     */
    @Test
    public void testGetY() {
        double expectedResult = 220;
        assertEquals(bird.getY(),expectedResult,0);
    }

    /**
     * Testataan setVelocityY-metodia.
     */
    @Test
    public void testSetVelocityY() {
        double expectedResult = -1;
        bird.setVelocityY(-1);
        assertEquals(bird.getVelocityY(),expectedResult,0);
    }

    /**
     * Testataan move-metodia.
     */
    @Test
    public void testMove() {
        double y = bird.getY();
        double velocityY = bird.getVelocityY();
        double expectedResult = y+velocityY;
        bird.move();
        assertEquals(bird.getY(),expectedResult,0);
        
    
    }

    /**
     * Testataan getVelocityY-metodia.
     */
    @Test
    public void testGetVelocityY() {
        double expectedResult = 0;
        assertEquals(bird.getVelocityY(),expectedResult,0);
    }

   
    
}
