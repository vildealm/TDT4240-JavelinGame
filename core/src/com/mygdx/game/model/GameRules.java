package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.view.PlayScreen;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameRules {

    private int numberOfPlayers;
    private String name;
    private ArrayList<Player> players;
    private Player player1;

    public GameRules(){
        this.player1 = new Player();
        players = new ArrayList<Player>();
        players.add(player1);
        /*for(int i = 0; i< players;i++){
            name = "player" + i;
            Player name = new Player();
            playerList.add(name);
        }*/
    }


    public void playGame(){
        for (Player player : players ){

        }
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



}
