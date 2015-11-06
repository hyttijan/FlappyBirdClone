/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import hyttijan.flappybirdclone.Model.GameState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author janne
 */
public class Controller implements Runnable{
    private View view;
    private Model model;
    private Thread thread;
    public Controller(View view,Model model){
        this.model = model;
        this.view = view;
        this.thread = new Thread(this);
        
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
    public void startGame(){
       
        model.changeGameState(GameState.GAME);
        this.thread.start();
    }
    public void highscores(){
        model.changeGameState(GameState.GAME);
    }
    public void menu(){
         model.changeGameState(GameState.MENU);
    }
    public void fly(){
        model.fly();
    }

    @Override
    public void run() {
        
        while(model.getGameState()==GameState.GAME){
            try {
                model.update();
                view.update();
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
