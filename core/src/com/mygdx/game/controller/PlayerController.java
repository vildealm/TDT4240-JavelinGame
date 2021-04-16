package com.mygdx.game.controller;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;
import java.util.Random;

public class PlayerController {

    private Player player;

    public PlayerController(){
        this.player = new Player();


    }
/*
    public void movement(){
        if(speedX > 30){
            speedX--;
        }

    }

    public void runningControls(){

            if(speedX<400){
                speedX = speedX + 20;
            }

        posX += Gdx.graphics.getDeltaTime() * speedX;

    }

    public void kast(){
            currentAnim = throwingMan;

    }

 */

}
