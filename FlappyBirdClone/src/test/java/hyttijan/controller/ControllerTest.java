/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.controller;

import hyttijan.view.View;
import hyttijan.model.Model;
import hyttijan.model.Model.GameState;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hyttijan
 */
public class ControllerTest {
    Controller controller;
    View view;
    Model model;
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    view = new View();
    model = new Model();
    controller = new Controller(view,model);
    view.registerController(controller);
    
    }
    
    @After
    public void tearDown() {
    }
    

    

    
     /**
     * Testataan startGame-metodia ja että se muuttaa pelin tilan GameState.Gameksi ja että kaikki pelin komponentit on alustettu.
     */
    @Test
    public void testStartGame() {
        this.controller.startGame();
        assertEquals(controller.getModel().getGameState(),GameState.GAME);
        assertNotNull(controller.getModel().getBird());
        assertNotNull(controller.getModel().getBlocks());
        assertNotNull(controller.getModel().getGameBg());       
    }
    @Test
    public void testClick() {
        controller.startGame();
        controller.click();
        assertEquals(controller.getModel().getBird().getVelocityY(),-0.5,0);

    }
    /*
    ** Testataan että run-toimii oikein kuin GameState on game
    */
    @Test
    public void testRun(){
        controller.startGame();
        controller.run();
        assertEquals(controller.getModel().getBird().getVelocityY(),0.00981,0);
        assertEquals(controller.getModel().getBlocks().get(0).getX(),639,0);
        assertEquals(controller.getModel().getBlocks().get(1).getX(),1039,0);
        assertEquals(controller.getModel().getBlocks().get(2).getX(),1439,0);
    }
    
    
}
