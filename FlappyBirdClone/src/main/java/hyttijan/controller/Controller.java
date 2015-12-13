/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.controller;

import hyttijan.model.Model;
import hyttijan.view.View;
import javax.swing.SwingUtilities;


/**
 *
 * @author hyttijan
 */
public class Controller implements Runnable{
    private View view;
    private Model model;
    private Thread thread;
    public Controller(View view,Model model){
        this.model = model;
        this.view = view;
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Model model = new Model();
                View view = new View();
                Controller controller = new Controller(view,model);
                view.registerController(controller);
            }
        
        });
    }
    public Model getModel(){
        return this.model;
    }
    /*
    **Metodi kutsuu mallin metodia alustamaan pelin.
    */
    public void startGame(){ 
        this.model.init();
       
  
        
        
    }
    public void highscores(){
        model.changeGameState(Model.GameState.HIGHSCORE);
        
    }
    public void menu(){
         model.changeGameState(Model.GameState.MENU);
    }
    public void newHighscore(String name){
        model.newHighscore(name);
    }
    /**
    *Metodi käskee mallia suorittamaan fly-metodin jos peli on alkanut.
    */
    public void click(){
        if(model.getGameState()==Model.GameState.GAME){   
        model.fly();
        }
    }
    /**
    *
    */
    @Override
    public void run() {
         try {
        while(true){
            if(model.getGameState()==Model.GameState.GAME){   
                model.update();
                view.update();
           
            }
            Thread.sleep(5);
        }
       
         } catch (InterruptedException ex) {
             
         }
        
      
    }
    
}
