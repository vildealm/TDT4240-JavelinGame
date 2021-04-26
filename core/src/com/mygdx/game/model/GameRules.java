package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;

import java.util.ArrayList;

public class GameRules {

    private int numberOfPlayers;
    private ArrayList<Player> players;

    public GameRules(){
        
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void clearPlayers(){
        players.clear();
    }

}
