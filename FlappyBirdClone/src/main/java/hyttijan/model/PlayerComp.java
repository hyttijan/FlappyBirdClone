/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hyttijan.model;

import java.util.Comparator;

/**
 *
 * @author hyttijan
 */

    public class PlayerComp implements Comparator<Player>{
        /**
        *Metodi vertaa kahta pelaajaa keskenään ja palauttaa arvon pelaajien pistemäärän perusteella.
        */    
        public int compare(Player player1, Player player2) {
        
        if(player1.getScore()<player2.getScore()){
            return 1;
        }
        else if(player2.getScore()<player1.getScore()){
            return -1;
        }
        else{
        return 0;
        }
    }
        }
    

