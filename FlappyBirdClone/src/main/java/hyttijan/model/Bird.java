/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author janne
 */
public class Bird {
    private double x,y;
    private double velocityY;
    private int height,width;
   
    private BufferedImage image;

    
       public Bird(double x,double y) throws IOException{
        this.x =x;
        this.y =y;
        this.velocityY = 0;
        this.height=36;
        this.width=44;
        ClassLoader cl = this.getClass().getClassLoader();
        this.image =  ImageIO.read(cl.getResource("flappy.png"));
           
            
         
       
    }
    
    public void setY(double y){
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public double getY(){
        return this.y;
    }
    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }
    /**
    *Metodi siirtää lintua y-kiihtyvyyden verran nykyisestä paikasta.
    */
    public void move(){
        this.y = this.y+this.velocityY;
    }
    public double getVelocityY(){
        return this.velocityY;
    }
 
    public void paintBird(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(this.velocityY, this.x+22, this.y+18);
        g2d.drawImage(image,(int)this.x,(int)this.y, null);
       g2d.setTransform(old);
      
        
    }
    
}
