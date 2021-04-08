package com.mygdx.game.model.states;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.JavelinGame;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;
    public JavelinGame game;

    public GameStateManager(JavelinGame game)
    {
        states = new Stack<State>();
        this.game = game;
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


    public void renderBatch() {
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("#GSM", String.valueOf(states.peek().getScreen()));
        states.peek().renderScreen();
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("#GSM", String.valueOf(states.peek().getScreen()));
        states.peek().getScreen().render(Gdx.graphics.getDeltaTime());

    }

    public Stack getStates() {
        return states;
    }
}
