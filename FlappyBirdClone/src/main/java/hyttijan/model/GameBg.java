/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author janne
 */
public class GameBg {
    private double x,x2;
    private int y;
    private BufferedImage image;
    public GameBg() throws IOException{
        this.x=0;
        this.y=0;
        this.x2=640;     
        ClassLoader cl = this.getClass().getClassLoader();
        this.image = ImageIO.read(cl.getResource("gamebg.jpeg"));
      
    }
    /**
    *Liikutetaan taustaa, kun tausta saavuttaa x-kordinaatin -640 siiretään se 640.
    */
    public void updateX(){
        if(this.x>-640){
        this.x=this.x-0.5;
        }
        else{
        this.x=640;
        }
        if(this.x2>-640){    
        this.x2=this.x2-0.5;
        }
        else{
        this.x2=640;
        }
    }
    public double getX(){
        return this.x;
    }
    public double getX2(){
        return this.x2;
    }
    
    public void paintGameBg(Graphics g){
      
        g.drawImage(image,(int)this.x,this.y,640,480, null);
        g.drawImage(image,(int)this.x2,this.y,640,480, null);
      
       
    }
}
