/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyttijan.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author janne
 */
 public class Player implements Serializable{
        private String name;
        private int score;
        public Player(String name,int score){
            this.name = name;
            this.score = score;
        }
        public int getScore(){
            return this.score;
        }
        public String getName(){
            return this.name;
        }
        
    @Override
    public boolean equals(Object player){
        if(this.hashCode()==player.hashCode()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.score;
        return hash;
    }
    }