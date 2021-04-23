package com.mygdx.game.model.states;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.GameRules;
import com.mygdx.game.model.components.Player;

import java.util.ArrayList;
import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;
    public JavelinGame game;
    private SpriteBatch sb;
    private GameRules gameRules;

    public GameStateManager(JavelinGame game)
    {
        states = new Stack<State>();
        this.game = game;
        this.gameRules = new GameRules();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        pop();
        states.push(state);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#GSM", String.valueOf(states));
    }

    public void update(float dt){
        states.peek().update(dt);

    }

    public void renderBatch(SpriteBatch sb) {
        states.peek().renderScreen();
        states.peek().getScreen().render(Gdx.graphics.getDeltaTime(), sb);

    }

    public Stack getStates() {
        return states;
    }

    public GameRules getGameRules() {
        return this.gameRules;
    }

    public void setGameRules(ArrayList<Player> players) {
        this.gameRules.setPlayers(players);
    }
}
