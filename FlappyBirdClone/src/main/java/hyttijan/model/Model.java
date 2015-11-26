/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author janne
 */
public class Model {
    
    public enum GameState{
        MENU,GAME,GAMEOVER,HIGHSCORE,NEWRECORD,ERROR
    }
    private HighscoreList highscoreList;
    private Bird bird;
    private GameBg gameBg;
    private GameState gameState;
    private ArrayList<Block> blocks;
    private int points;
    private String errorMessage;

    private final static double gravity = 0.00981;
    
    public Model(){
       this.gameState = GameState.MENU;
       this.highscoreList = new HighscoreList();
        try {
          
            this.highscoreList.readFile();
        } catch (IOException ex) {
           this.errorMessage ="Could not read highscorelist.dat";
           this.gameState=GameState.ERROR;
        } catch (ClassNotFoundException ex) {
           
             this.errorMessage ="Could not find class HighscoreList";
           this.gameState=GameState.ERROR;
        }
       
    }
    
    public HighscoreList getHighscoreList(){
        return this.highscoreList;
    }
    /**
    *Pelin alustaja. Tätä kutsutaan kun varsinainen peli alkaa.
    **/
    public void init(){
       try{
       this.bird = new Bird(290,220);
       this.gameBg = new GameBg();
       this.points = 0;
       this.blocks = new ArrayList<Block>();
       this.blocks.add(new Block(640));
       this.blocks.add(new Block(1040));
       this.blocks.add(new Block(1440));
       this.gameState=GameState.GAME;
       }
       catch(IOException e){
           this.errorMessage ="Could not load all graphics";
           this.gameState=GameState.ERROR;
           
       }
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
    public int getPoints(){
        return this.points;
    }
    /**
     * Metodi muuttaa pelitilaa saamansa parametrin mukaan.
     * @param state 
     */
    public void changeGameState(GameState state){
       this.gameState = state;
       
    }
    public ArrayList<Block> getBlocks(){
        return this.blocks;
    }
    /**
    *Metodi kasvattaa pisteitä yhdellä.
    */
    public void addPoints(){
        this.points++;
    }
    /**
    *Metodi lähettää olion highscorelist kirjoitettavaksi uuden Player olion, parametrina saatu nimi tulee Player olion nimeksi.
    *Metodi käsittelee myös tilanteen,jossa syntyy poikkeus. 
    */
    public void newHighscore(String name){
        try {
            this.highscoreList.writePlayer(new Player(name, this.points));
        } catch (IOException ex) {
            this.errorMessage ="Could not write to the highscore file. The file may be corrupted";
            this.gameState = gameState.ERROR;
        }
        
    }
    /**
    *Metodi päivittää pelitilannetta.
    */
     public void update(){
        this.gameBg.updateX();
        this.bird.setVelocityY(this.bird.getVelocityY()+gravity);
        this.bird.move();
        if(collissionBoundaries()){
             gameOverOrNewRecord();
        }
        for(int i=0;i<this.blocks.size();i++){
            this.blocks.get(i).move();
                if(collission(this.blocks.get(i))){
                   gameOverOrNewRecord();
                }
                if(this.blocks.get(i).getX()+this.blocks.get(i).getWidth()<290&&this.blocks.get(i).getPoints()){
                   this.blocks.get(i).setPoints(false);
                   this.addPoints();
                } 
        }
    }
     /**
     *Metodi tarkistaa syntyykö törmäys ylä- tai alarajaan.
     */
      public boolean collissionBoundaries(){
        boolean collissionBoundaries = this.bird.getY()<0|this.bird.getY()+this.bird.getHeight()>480;
        return collissionBoundaries;
    }
      /**
      *Metodi määrittää syntyykö uusi ennätys vai päättyykö peli vain.
      */
     public void gameOverOrNewRecord(){
        if(newRecord()){
            this.gameState=GameState.NEWRECORD; 
        }
        else{
            this.gameState=GameState.GAMEOVER; 
        }
   }
     /**
     *Metodi tarkistaa oikeuttaako pelaajan tulos ennätyslistoille.
     */
   public boolean newRecord(){
    if(this.getHighscoreList().getPlayers().size()>=10){
       if(this.points>this.getHighscoreList().getPlayers().get(9).getScore()){
       return true;
       }
    return false;   
    }   
       return true;
   }
  /**
   *Metodi tarkistaa tapahtuuko törmäys Bird olion ja Block olion välillä.
   */
    public boolean collission(Block block){
        boolean collissionX = this.bird.getX()+this.bird.getWidth()>=block.getX()&&this.bird.getX()<=block.getX()+block.getWidth();
        boolean collissionY = this.bird.getY()+this.bird.getHeight()>=block.getY()|this.bird.getY()<=block.getY2();
        return collissionX&&collissionY;
        
    }
    /**
    *Metodi kasvattaa Bird olion y-kiihtvvyyttä.
    */
    public void fly(){
        if(this.bird.getVelocityY()>0){
           this.bird.setVelocityY(-1);    
        }
        else{
            this.bird.setVelocityY(this.bird.getVelocityY()-0.5);
        }
     
    }
    public Bird getBird(){
        return this.bird;
    }
    public GameBg getGameBg(){
        return this.gameBg;
    }
    public GameState getGameState(){
       return this.gameState; 
    }
    
    
}
