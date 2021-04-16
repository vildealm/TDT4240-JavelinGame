package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;

public class PlayerController {

    private String username, country;
    private double score;
    private Sprite sprite;
    private Player player;

    public PlayerController(){
        this.username = username;
        this.score = score;
        this.country = country;
        this.player = player;
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

    public Player getPlayer(){
        return player;
    }
    public void setPlayer(){
        this.player = player;
    }
}
