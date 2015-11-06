/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.flappybirdclone;

import hyttijan.flappybirdclone.HighscoreList.Player;
import hyttijan.flappybirdclone.Model.GameState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author janne
 */
public class View extends JFrame {
    private Controller controller;
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private Dimension dimension;
   
   
    
    public View(){
        init();
    }
    
    public final void init(){
        dimension = new Dimension(640,480);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setTitle("Flappy Bird clone");
        this.gamePanel = new GamePanel();
        this.getContentPane().add(this.gamePanel);
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
   
    
    public void registerController(Controller controller){
        this.controller = controller;
        this.gamePanel.registerController(controller);
       

    }
    
    public void update(){
    this.gamePanel.repaint();
    }
    private class ButtonListener implements ActionListener{
       
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==gamePanel.startGame){
            
                controller.startGame();
                gamePanel.startGame.setVisible(false);
                gamePanel.highscores.setVisible(false);
                gamePanel.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    controller.fly();
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}                     
                });
 
            }
           
        }
        
  
    }
    
    private class GamePanel extends JPanel{
        private Controller controller;
        private JButton startGame;
        private JButton highscores;
        private ButtonListener buttonListener;
        private Font font;
        
        public GamePanel(){
        
        this.buttonListener = new ButtonListener();
        this.startGame = new JButton("Start game");
        this.startGame.addActionListener(buttonListener);
     
        this.highscores = new JButton("Highscores");
        this.highscores.addActionListener(buttonListener);
        this.add(this.startGame);
        this.add(this.highscores);
        this.font = new Font("Serif",Font.PLAIN,36);
        
        
        }
        public void registerController(Controller controller){
            this.controller = controller;
         
        
        }
       
        
        @Override
        public void paintComponent(Graphics g){
            Model model = controller.getModel();
            if(model.getGameState()==GameState.GAME){
                model.getGameBg().paintGameBg(g);
                model.getBird().paintBird(g);
                for(int i=0;i<model.getBlocks().size();i++){
                    model.getBlocks().get(i).paintBlock(g);
                }
                g.setColor(Color.red);
                g.setFont(this.font);
                g.drawString(Integer.toString(model.getPoints()),600, 440);
            }
            else if(model.getGameState()==GameState.GAMEOVER){
                g.setColor(Color.red);
                g.setFont(this.font);
                g.drawString("GAME OVER",290,240);
            }
            else if(model.getGameState()==GameState.HIGHSCORE){
           
            }
            
        }

        
    }
   
    
}
