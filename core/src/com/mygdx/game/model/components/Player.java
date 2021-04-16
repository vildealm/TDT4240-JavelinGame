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
    private int random;
    private int posX = 20;
    private int speedX = 0;
    private double attempt;

    public Player(){
        //Score

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

    public void setScore(double speed, double dist) {
        this.score = (double)Math.round(((5*(speed/10)*(15-dist))/15) * 100d) / 100d;
    }

    //Player


    //Player
    /*if(posX< 545){
        runningControls();
    }else{
        attempt = (double)Math.round(setScore(speedX, random) * 100d) / 100d;
    }

     */


    public void setCountry(String country) {
        this.country = country;
    }



}
