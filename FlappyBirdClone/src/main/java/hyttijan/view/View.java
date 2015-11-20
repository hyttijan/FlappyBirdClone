/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.view;




import hyttijan.controller.Controller;
import hyttijan.model.Model;
import hyttijan.model.Model.GameState;
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
import javax.swing.JTextField;

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
    this.gamePanel.update();
  
    
    
    }
    private class ButtonListener implements ActionListener{
       
       
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==gamePanel.startGame){
                controller.startGame();
                gamePanel.update();
            }
            else if(e.getSource()==gamePanel.highscores){    
                controller.highscores();
                gamePanel.update();
             
            }
            else if(e.getSource()==gamePanel.submit){
                String name = gamePanel.name.getText();
                controller.newHighscore(name);
                controller.highscores();
                gamePanel.update();
            }
            else if(e.getSource()==gamePanel.back){
                controller.menu();
                gamePanel.update();
                
            }
           
        }
        
  
    }
    
    private class GamePanel extends JPanel{
        private Controller controller;
        private JButton startGame;
        private JButton highscores;
        private JButton back;
        private JButton submit;
        private JTextField name;
        
        private ButtonListener buttonListener;
        private Font font,font2;
       
        public GamePanel(){
        
        this.buttonListener = new ButtonListener();
        this.startGame = new JButton("Start game");
        this.startGame.addActionListener(buttonListener);
        this.highscores = new JButton("Highscores");
        this.highscores.addActionListener(buttonListener);
        this.back = new JButton("Back");
        this.back.addActionListener(buttonListener);
        this.back.setVisible(false);
        this.name = new JTextField(12);
        this.name.setVisible(false);
        this.submit = new JButton("Submit");
        this.submit.addActionListener(buttonListener);
        this.submit.setVisible(false);
        
        this.add(this.startGame);
        this.add(this.highscores);
        this.add(this.back);
        this.add(this.name);
        this.add(this.submit);
        this.font = new Font("Serif",Font.PLAIN,36);
        this.font2 = new Font("Serif",Font.PLAIN,20);
        this.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    controller.click();
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
        
       
        public void registerController(Controller controller){
            this.controller = controller;        
        }
        public void update(){
            if(controller.getModel().getGameState()==GameState.MENU){
                this.back.setVisible(false);
                this.startGame.setVisible(true);
                this.highscores.setVisible(true);
            }
            else if(controller.getModel().getGameState()==GameState.GAMEOVER){
                this.startGame.setVisible(true);
                this.highscores.setVisible(true);
            }
            else if(controller.getModel().getGameState()==GameState.NEWRECORD){
                this.name.setVisible(true);
                this.submit.setVisible(true);
            }
            else if(controller.getModel().getGameState()==GameState.HIGHSCORE){
                this.name.setVisible(false);
                this.submit.setVisible(false);
                this.startGame.setVisible(false);
                this.highscores.setVisible(false);
                this.back.setVisible(true);
                
            }
            else if(controller.getModel().getGameState()==GameState.GAME){
                this.startGame.setVisible(false);
                this.highscores.setVisible(false);
            }
            this.repaint();
        }
       
        
        @Override
        public void paintComponent(Graphics g){
            Model model = controller.getModel();
            if(model.getGameState()==GameState.GAME){
             paintGame(g,model);
            }
            else if(model.getGameState()==GameState.GAMEOVER){
                paintGame(g,model);
                g.setColor(Color.red);
                g.setFont(this.font);
                g.drawString("GAME OVER",290,240);
            }
            else if(model.getGameState()==GameState.NEWRECORD){
                paintGame(g,model);
                g.setColor(Color.red);
                g.setFont(this.font);
                g.drawString("NEW RECORD",290,240);
            }
            else if(model.getGameState()==GameState.HIGHSCORE){
                g.setColor(Color.gray);
                g.clearRect(0, 0, 640, 480);
                g.setColor(Color.red);
                g.setFont(this.font2);
                int y= 50;
                for(int i=0;i<model.getHighscoreList().getPlayers().size();i++){
                   g.drawString(i+1+". Player:"+model.getHighscoreList().getPlayers().get(i).getName()+" Score:"+model.getHighscoreList().getPlayers().get(i).getScore(), 290, y);
                y=y+30;
                }
            }
            else{
                 g.setColor(Color.gray);
                 g.clearRect(0, 0, 640, 480); 
            }
            

        
    }
        public void paintGame(Graphics g,Model model){
                model.getGameBg().paintGameBg(g);
                model.getBird().paintBird(g);
                for(int i=0;i<model.getBlocks().size();i++){
                    model.getBlocks().get(i).paintBlock(g);
                }
                g.setColor(Color.red);
                g.setFont(this.font);
                g.drawString(Integer.toString(model.getPoints()),600, 440);
                  
        }
   
    
}
}
