/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author janne
 */
public class Block {
    private int x,y;
    private int  y2;
    private int width;
    private int imageHeadHeight;
    private BufferedImage imageHead,imageHead2,image,image2;
    private boolean points;
    public Block(int x) throws IOException{
        this.x=x;
        this.width =81;
        this.imageHeadHeight=45;
        this.points = true;
        generateY();
        ClassLoader cl = this.getClass().getClassLoader();

        this.imageHead = ImageIO.read(cl.getResource("blockHead.png"));
        this.imageHead2 = ImageIO.read(cl.getResource("blockHead2.png"));
        this.image = ImageIO.read(cl.getResource("block.png"));
        this.image2 = ImageIO.read(cl.getResource("block2.png"));
       
    }
    public int getX(){
        return this.x;
    }
    /**
    *Generoidaan y-kordinaatit Block-oliolle.
    */
    public void generateY(){
         this.y = (int)(Math.random()*240)+160;
         
         this.y2 = this.y-120;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setY2(int y2){
        this.y2 = y2;
    }
    public int getY(){
        return this.y;
    }
    public boolean getPoints(){
        return this.points;
    }
    
    public void setPoints(boolean points){
        this.points = points;
    }
    public int getWidth(){
        return this.width;
    }
    public int getY2(){
        return this.y2;
    }
    /**
    *Liikutetaan Block-oliota, kun olio saavuttaa x-kordinaatin -560 palautetaan se x-kordinaattiin 640.
    */
    public void move(){
        if(this.x>-560){
        this.x--;   
        }
        else{
        this.x = 640;
        generateY();
        this.points = true;
        }
        
     
    }
    public void paintBlock(Graphics g){
  
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imageHead,this.x, this.y, null);
        g2d.drawImage(image,this.x+3, this.y+this.imageHeadHeight,this.width,480-this.y+this.imageHeadHeight, null);    
        g2d.drawImage(imageHead2,this.x,this.y2-this.imageHeadHeight,null);
        g2d.drawImage(image2,this.x+3,0,this.width,this.y2-this.imageHeadHeight,null);
 
        
         
        
        
    }
    
}
