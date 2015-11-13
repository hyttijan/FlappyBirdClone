/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import java.util.ArrayList;

/**
 *
 * @author janne
 */
public class Model {
    public enum GameState{
        MENU,GAME,GAMEOVER,HIGHSCORE,NEWRECORD
    }
    private HighscoreList highscoreList;
    private Bird bird;
    private GameBg gameBg;
    private GameState gameState;
    private ArrayList<Block> blocks;
    private int points;

    private final static double gravity = 0.00981;
    
    public Model(){
       this.gameState = GameState.MENU;
       this.highscoreList = new HighscoreList();
       
    }
    public HighscoreList getHighscoreList(){
        return this.highscoreList;
    }
    public void init(){
      
       this.bird = new Bird(290,220);
       this.gameBg = new GameBg();
       this.points = 0;
       this.blocks = new ArrayList<Block>();
       this.blocks.add(new Block(640));
       this.blocks.add(new Block(1040));
       this.blocks.add(new Block(1440));
    }
    public int getPoints(){
        return this.points;
    }
    public void changeGameState(GameState state){
       this.gameState = state;
       
    }
    public ArrayList<Block> getBlocks(){
        return this.blocks;
    }
    public void addPoints(){
        this.points++;
    }
    public void newHighscore(String name){
        this.highscoreList.writePlayer(new Player(name, this.points));
        
    }
    public void update(){
        this.gameBg.updateX();
        this.bird.setVelocityY(this.bird.getVelocityY()+gravity);
        this.bird.move();
        if(this.bird.getY()<0|this.bird.getY()+this.bird.getHeight()>480){
             if(newRecord()){
                   this.gameState=GameState.NEWRECORD; 
             }
             else{
                   this.gameState=GameState.GAMEOVER; 
             }
           
        }
        
        
        for(int i=0;i<this.blocks.size();i++){
            this.blocks.get(i).move();
                if(collission(this.blocks.get(i))){
                   if(newRecord()){
                   this.gameState=GameState.NEWRECORD; 
                   }
                   else{
                   this.gameState=GameState.GAMEOVER;   
                   }
                }
                if(this.blocks.get(i).getX()+this.blocks.get(i).getWidth()<290&&this.blocks.get(i).getPoints()){
                   this.blocks.get(i).setPoints(false);
                   this.addPoints();
                } 
        }
    }
   public boolean newRecord(){
    if(this.getHighscoreList().getPlayers().size()==10){
       if(this.points>this.getHighscoreList().getPlayers().get(9).getScore()){
       return true;
       }
    return false;   
    }   
       return true;
   }
  
    public boolean collission(Block block){
        boolean collissionX = this.bird.getX()+this.bird.getWidth()>=block.getX()&&this.bird.getX()<=block.getX()+block.getWidth();
        boolean collissionY = this.bird.getY()+this.bird.getHeight()>=block.getY()|this.bird.getY()<=block.getY2();
        return collissionX&&collissionY;
        
    }
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
