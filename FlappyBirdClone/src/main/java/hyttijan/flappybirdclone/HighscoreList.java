/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janne
 */
public class HighscoreList implements Serializable {
    private File file;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ArrayList<Player> players;
         
    public HighscoreList(){
        this.players = new ArrayList<Player>();
        this.file = new File("highscoreList.dat");
        try {
            this.file.createNewFile();
             this.readFile();
        } catch (IOException ex) {
            Logger.getLogger(HighscoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
       
       
    }
    public void readFile(){
        try {
            this.ois  = new ObjectInputStream(new FileInputStream(this.file));
            Player player;
            try {
                while((player = (Player) this.ois.readObject())!=null){
                    this.players.add(player);
                }
            this.ois.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HighscoreList.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            
        }
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public void writePlayer(String name,int score){
        Player player = new Player(name,score);
        try {
            this.oos = new ObjectOutputStream(new FileOutputStream(this.file));
            this.oos.writeObject(player);
            this.oos.close();
        } catch (IOException ex) {
            Logger.getLogger(HighscoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public class Player implements Serializable{
        private String name;
        private int score;
        
        public Player(String name,int score){
        this.name = name;
        this.score = score;
        }
        public String getName(){
          return this.name;   
        }
        public int getScore(){
            return this.score;
        }
        
    }
    
   
    
    
}
