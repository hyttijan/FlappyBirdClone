/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janne
 */
public class HighscoreListTest {
    private HighscoreList highscoreList;
    public HighscoreListTest() {
    }
    

    
    @Before
    public void setUp() {
      this.highscoreList = new HighscoreList();
    
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Testataan ettei readFile() luo turhaan uutta tiedostoa.
     */
    @Test
    public void testReadFile1() {
        
        if(this.highscoreList.getFile().exists()){
           assertTrue(this.highscoreList.getPlayers().size()>0);
        }
        
    }
    /**
     * Testataan ettei readFile() palauta null-arvoa pelaajista.
     */
    @Test
    public void testReadFile2() {
        
        assertNotNull(this.highscoreList.getPlayers());
        
    }
    @Test
    public void testWritePlayer(){
        ArrayList expectedResult = this.highscoreList.getPlayers();
        Player newPlayer = new Player("test",29);
        expectedResult.add(newPlayer);
        Collections.sort(expectedResult,new PlayerComp());
        this.highscoreList.writePlayer(newPlayer);
        this.highscoreList.readFile();
        assertEquals(expectedResult,this.highscoreList.getPlayers());
    }
    @Test
    public void testWritePlayer2(){
        
        Player newPlayer = new Player("test",29);
        
        this.highscoreList.writePlayer(newPlayer);
        this.highscoreList.readFile();
        
        assertTrue(this.highscoreList.getPlayers().size()<=10);
    }

    /**
     * Testataan että tiedostoon kirjoitus toimii halutulla tavalla ja palauttaa takaisin saman ArrayListin,
     * joka sinne kirjoitettiin.
     */
    @Test
    public void testWriteToFile(){
        ArrayList<Player> test = this.highscoreList.getPlayers();
        this.highscoreList.writeToFile();
        this.highscoreList.readFile();
        assertEquals(test,this.highscoreList.getPlayers());
    }
    

    
}
