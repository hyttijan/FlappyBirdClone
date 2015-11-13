/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import hyttijan.flappybirdclone.Model.GameState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janne
 */
public class ModelTest {
    Model model;
    public ModelTest() {
    }
   
    @Before
    public void setUp() {
        model = new Model();
        model.init();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testataan että getPoints-metodi palauttaa pisteet.
     */
    @Test
    public void testGetPoints() {
        
        int expResult = 0;
        assertEquals(model.getPoints(), expResult);
    }
    /**
     * Testataan että getPoints-metodi toimii pisteiden lisäyksen jälkeenkin.
     */
    public void testGetPoints2(){
        int expResult = 1;
        model.addPoints();
        assertEquals(model.getPoints(),expResult);
    }

    /**
     * Testataan että kaikki GameStatet toimivat.
     */
    @Test
    public void testChangeGameState1() {
        model.changeGameState(Model.GameState.MENU);
        assertEquals(GameState.MENU,model.getGameState());
    }
     @Test
    public void testChangeGameState2() {
        model.changeGameState(Model.GameState.GAME);
        assertEquals(GameState.GAME,model.getGameState());
    }
     @Test
    public void testChangeGameState3() {
        model.changeGameState(Model.GameState.GAMEOVER);
        assertEquals(GameState.GAMEOVER,model.getGameState());
    }
     @Test
    public void testChangeGameState4() {
        model.changeGameState(Model.GameState.HIGHSCORE);
        assertEquals(GameState.HIGHSCORE,model.getGameState());
    }

    /**
     * Testataan että getBlocks-metodi ei palauta null-arvoa.
     */
    @Test
    public void testGetBlocks() {
     assertNotNull(model.getBlocks());
    }

    /**
     * Testataan että newRecord-metodi toimii oikein.
     */
    @Test
    public void testNewRecord() {
        /**
        *Jos listassa on jo 10. pelaajaa, ei uutta pelaajaa lisätä automaattisesti listaan.
        */
        if(this.model.getHighscoreList().getPlayers().size()==10){
            /**
            *Suoritetaan looppia, jossa lisätään koko ajan pisteitä, niin kauan kunnes pelaajalla on yhtä paljon
            * kuin listan viimeisellä. Tämän ei pitäisi riittää listalle pääsyyn
            */
        for(int i=0;i<this.model.getHighscoreList().getPlayers().get(9).getScore();i++){
             this.model.addPoints();
            assertFalse(this.model.newRecord());
        }
        /**
         * Nyt lisätään pisteitä vielä kerran, tämän pitäisi riittää listalle pääsyyn.
        */
        this.model.addPoints();
        assertTrue(this.model.newRecord());
        }
        /**
        *Jos pelaajia on vähemmän kuin kymmenen pääsee automaattisesti listoille.
        */
        else{
        assertTrue(this.model.newRecord());
        }
    
    
       
    }

    /**
     * Testataan että linnun törmäys toimii oikein palikoihin.
     */
    @Test
    public void testCollission1() {
        Block block = new Block(100);
        assertFalse(model.collission(block));
    }
    @Test
    public void testCollission2() {
        Block block = new Block(640);
        assertFalse(model.collission(block));
    }
    @Test
    public void testCollission3() {
        Block block = new Block(290);
        block.setY(221);
        block.setY2(600);
        assertTrue(model.collission(block));
    }
      @Test
    public void testCollission4() {
        Block block = new Block(290);
        block.setY(640);
        block.setY2(264);
        assertTrue(model.collission(block));
    }
      @Test
    public void testCollission5() {
        Block block = new Block(290);
        block.setY(640);
        block.setY2(219);
        assertFalse(model.collission(block));
    }

    /**
     * Testataan että lentäminen muuttaa linnun y-kiihtyvyyttä negatiiviseksi (koska y-kordinaatisto osoittaa alas).
     */
      @Test
    public void testFly() {
               
        model.fly();
        assertTrue(model.getBird().getVelocityY()<0);
        
    }
    /**
     * Testataan että lentämisen jälkeen y-kiihtyvyys on pienempi kuin sitä ennen.
     */
    @Test
    public void testFly2() {
        /**
         * Otetaan ensiksi nykyinen korkeus, sitten lennetään ja testataan että korkeus on "suurempi" 
         * eli tässä tapauksessa pienempi,koska y-kordinaatisto osoittaa alaspäin. 
         */
        double velocityY = model.getBird().getVelocityY();        
        model.fly();
        assertTrue(model.getBird().getVelocityY()<velocityY);
        
    }

    /**
     * Testataan että getBird-metodi ei palauta null-arvoa.
     */
    @Test
    public void testGetBird() {
        assertNotNull(model.getBird());
    }

    /**
     * Testataan että getGameBg-metodi ei palauta null-arvoa.
     */
    @Test
    public void testGetGameBg() {
       assertNotNull(model.getGameBg());
           }

    /**
     * Testataan että getGameState-metodi ei palauta null-arvoa.
     */
    @Test
    public void testGetGameState() {
       assertNotNull(model.getGameState()); 
    }
    
}
