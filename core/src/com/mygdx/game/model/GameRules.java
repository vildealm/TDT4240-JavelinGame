package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.view.PlayScreen;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameRules {

    private GameStateManager gsm;
    private String name;
    private ArrayList<Player> players;
    double tempScore;

    public GameRules(GameStateManager gsm, ArrayList<Player> players){
        this.gsm = gsm;
        this.players = players;
    }

    public void playGame(){

    }



}
