/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.util.ArrayList;
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
     * Testataan ettei readFile() palauta null-arvoa pelaajista.
     */
    @Test
    public void testReadFile() {
       
        assertNotNull(this.highscoreList.getPlayers());
        
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
