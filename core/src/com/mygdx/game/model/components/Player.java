package com.mygdx.game.model.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Assets;

import java.util.Random;

public class Player extends Actor {

    private String username;
    private Double score;
    private String country;


    public Player(){

    }

    public String getUsername(){
        return this.username;
    }

    public Double getScore(){
        return this.score;
    }

    public String getCountry() {
        return this.country;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void calculateScore(double speed, double dist) {
        if(dist<0){
            this.score = 0.0;
        }
        else{
            this.score = (double)Math.round(((5*(speed/10)*(650-dist))/650) * 100d) / 100d;
        }

    }

    public void setScore(double score){
        this.score = score;
    }


    public void setCountry(String country) {
        this.country = country;
    }

}
