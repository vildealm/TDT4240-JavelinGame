package com.mygdx.game.model.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Assets;

public class Player extends Actor {

    private String username;
    private Double score;
    private String country;

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

    public void setScore(Double score) {
        this.score = score;
    }


    public void setCountry(String country) {
        this.country = country;
    }



}
