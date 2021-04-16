package com.mygdx.game.model.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Assets;

public class Score extends Actor {

    private String username;
    private Double score;
    private String country;
    private Texture texture;
    private Sprite sprite;


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

    public Score(String username, Double score, String country){
        this.username = username;
        this.score = score;
        this.country = country;
        sprite =  new Sprite(Assets.getTexture(Assets.player));
    }
}
