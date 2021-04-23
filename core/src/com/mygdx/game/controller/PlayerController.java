package com.mygdx.game.controller;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;
import java.util.Random;

public class PlayerController {

    private Player player;
    private int speed;
    private int dist;

    public PlayerController(){
        this.player = new Player();
        speed = 0;
        dist = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void reduceSpeed(){
        if(speed > 30){
            speed--;
        }
    }

    public void increaseSpeed(){
            if(speed<600){
                speed = speed + 20;
            }
    }


}
