/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author janne
 */
public class HighscoreList{
    private File file;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ArrayList<Player> players;

         
    public HighscoreList(){
       
       this.players = new ArrayList<Player>();
              
    }
    /**
    *Metodi lukee highscorelista.dat tiedoston, jos se on olemassa.
    */
    
    public void readFile() throws IOException, ClassNotFoundException{
     
     this.file = new File("highscores.dat");
            /**
             *Jos tiedosto on olemassa luetaan se.
             */
           
            if(this.file.exists()){
                
                
                 
            /**
             * Yritetään lukea ArrayListia pelaajista, jos tiedosto on tyhjä
             * otetaan poikkeus kiinni ja luodaan tyhjä ArrayList-pelaajista.
             */
           this.ois = new ObjectInputStream(new FileInputStream(this.file));
           
           this.players = (ArrayList <Player>)this.ois.readObject();
          
           
           this.ois.close();
           
           }
           
        
            
        
    }
    
    public File getFile(){
        return this.file;
    }
    /**
    * Lisätään uusi pelaaja ArrayListiin ja lajitellaan pelaajat pisteiden mukaan, poistetaan 11.pelaaja.
    */
    public void writePlayer(Player player) throws IOException{
       
        this.players.add(player);
        Collections.sort(players,new PlayerComp());
      
        if(this.players.size()>10){
        for(int i=10;i<this.players.size();i++){
            this.players.remove(i);
        }
        }
      
        this.writeToFile();
    }
    /**
    * Kirjoitetaan uusi ArrayList tiedostoon.
    */
    public void writeToFile() throws IOException{
        
            
            this.oos = new ObjectOutputStream(new FileOutputStream(this.file));
            this.oos.writeObject(this.players);
            this.oos.close();
           
        
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }    
    
}
