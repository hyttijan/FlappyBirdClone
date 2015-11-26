/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author janne
 */
public class HighscoreListTest {
    private Model model;
    private HighscoreList highscoreList;
    public HighscoreListTest() {
    }
    

    
    @Before
    public void setUp() throws IOException, ClassNotFoundException {
      this.model = new Model();
      
      
      this.model.getHighscoreList().readFile();
    
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Testataan ettei readFile() luo turhaan uutta tiedostoa.
     */
    @Test
    public void testReadFile1() {
        
        if(this.model.getHighscoreList().getFile().exists()){
           assertTrue(this.model.getHighscoreList().getPlayers().size()>0);
        }
        
    }
    /**
     * Testataan ettei readFile() palauta null-arvoa pelaajista.
     */
    @Test
    public void testReadFile2() {
        
        assertNotNull(this.model.getHighscoreList().getPlayers());
        
    }
    @Test
    public void testWritePlayer() throws IOException, ClassNotFoundException{
        ArrayList expectedResult = this.model.getHighscoreList().getPlayers();
        Player newPlayer = new Player("test",29);
        expectedResult.add(newPlayer);
        Collections.sort(expectedResult,new PlayerComp());
        this.model.getHighscoreList().writePlayer(newPlayer);
        this.model.getHighscoreList().readFile();
        assertEquals(expectedResult,this.model.getHighscoreList().getPlayers());
    }
    @Test
    public void testWritePlayer2() throws IOException, ClassNotFoundException{
        
        Player newPlayer = new Player("test",29);
        
        this.model.getHighscoreList().writePlayer(newPlayer);
        this.model.getHighscoreList().readFile();
        
        assertTrue(this.model.getHighscoreList().getPlayers().size()<=10);
    }
      @Test
    public void testWritePlayer3() throws IOException, ClassNotFoundException{
        for(int i=0;i<13;i++){
            
        
        Player newPlayer = new Player("test",i);
        this.model.getHighscoreList().writePlayer(newPlayer);
        }
        
        this.model.getHighscoreList().readFile();
        
        assertTrue(this.model.getHighscoreList().getPlayers().size()<=10);
    }

    /**
     * Testataan että tiedostoon kirjoitus toimii halutulla tavalla ja palauttaa takaisin saman ArrayListin,
     * joka sinne kirjoitettiin.
     */
    @Test
    public void testWriteToFile() throws IOException, ClassNotFoundException{
        ArrayList<Player> test = this.model.getHighscoreList().getPlayers();
        this.model.getHighscoreList().writeToFile();
        this.model.getHighscoreList().readFile();
        assertEquals(test,this.model.getHighscoreList().getPlayers());
    }
    

    
}
