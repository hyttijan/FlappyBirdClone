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
import java.util.logging.Level;
import java.util.logging.Logger;

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
       this.file = new File("highscorelist.dat");
       this.readFile();       
    }
    public void readFile(){
        try {
            /**
             * Jos tiedostoa ei ole olemassa luodaan se.
             */
            if(this.file.createNewFile()){
                this.players = new ArrayList<>();
            }
            /**
             * Muussa tapauksessa luetaan tiedostosta.
             */
            else{
                
            
            /**
             * Yritetään lukea ArrayListia pelaajista, jos tiedosto on tyhjä
             * otetaan poikkeus kiinni ja luodaan tyhjä ArrayList-pelaajista.
             */
           this.ois = new ObjectInputStream(new FileInputStream(this.file));
           this.players = (ArrayList <Player>)this.ois.readObject();
           this.ois.close();
           }
        } catch (IOException ex) {
          this.players = new ArrayList<>();
        } catch (ClassNotFoundException ex) {
          
        }
            
        
    }
    public File getFile(){
        return this.file;
    }
    public void writePlayer(Player player){
        /**
         * Lisätään uusi pelaaja ArrayListiin ja lajitellaan pelaajat pisteiden mukaan, poistetaan 11.pelaaja.
         */
        this.players.add(player);
        Collections.sort(players,new PlayerComp());
        if(this.players.size()>10){
        this.players.removeAll(players.subList(10,players.size()-1));
        }
        this.writeToFile();
    }
    
    public void writeToFile(){
        /**
         * Kirjoitetaan uusi ArrayList tiedostoon.
        */
        try {
            this.oos = new ObjectOutputStream(new FileOutputStream(this.file));
            this.oos.writeObject(this.players);
            this.oos.close();
        } catch (IOException ex) {
            Logger.getLogger(HighscoreList.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
   
    
   
    
    
}
