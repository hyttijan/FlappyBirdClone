/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.controller;

import hyttijan.model.Model;
import hyttijan.view.View;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
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
    
        
    @Before
    public void setUp() {
    view = new View();
    model = new Model();
    controller = new Controller(view,model);
    view.registerController(controller);
    
    }
    
    @Test
    public void testMain(){
        
        assertNotNull(controller.getModel());
        assertNotNull(view);
    }
   
    
    
    

    

    
     /**
     * Testataan startGame-metodia ja että se muuttaa pelin tilan GameState.Gameksi ja että kaikki pelin komponentit on alustettu.
     */
    @Test
    public void testStartGame() {
        this.controller.startGame();
        assertEquals(controller.getModel().getGameState(),Model.GameState.GAME);
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
   

    
}