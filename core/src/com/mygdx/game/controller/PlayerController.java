package com.mygdx.game.controller;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.components.PlayerInputBox;

import java.util.ArrayList;
import java.util.Random;

public class PlayerController {

    private int speed;
    private int dist;


    public PlayerController(){
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

    //Checks if username is correctly written
    public boolean checkInputFields(ArrayList<PlayerInputBox> elements) {
        Boolean isValid = true;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getUsername().isEmpty() ||
                    elements.get(i).getUsername().contains("æ") ||
                    elements.get(i).getUsername().contains("ø") ||
                    elements.get(i).getUsername().contains("å") ||
                    elements.get(i).getUsername().contains(" ") ||
                    elements.get(i).getUsername().length() > 15 ||
                    elements.get(i).getCountry().contains("---")) {
                isValid = false;
            }
        }
        return isValid;
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
