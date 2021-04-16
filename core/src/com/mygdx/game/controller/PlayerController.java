package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.Assets;

public class PlayerController {

    private String username, country;
    private double score;
    private Sprite sprite;

    public PlayerController(){
        this.username = username;
        this.score = score;
        this.country = country;
        sprite =  new Sprite(Assets.getTexture(Assets.player));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
