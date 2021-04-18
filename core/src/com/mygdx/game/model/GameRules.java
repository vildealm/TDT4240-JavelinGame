package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameRules {

    private int numberOfPlayers;
    private String name;
    private ArrayList<Player> players;

    public GameRules(ArrayList<Player> players){
        players = new ArrayList<Player>();
        /*for(int i = 0; i< players;i++){
            name = "player" + i;
            Player name = new Player();
            playerList.add(name);
        }*/
    }

    public void playGame(){

    }



}
