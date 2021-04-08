package com.mygdx.game.model.states;
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
        states.pop();
        states.push(state);
    }


    public void update(float dt){
        states.peek().update(dt);

    }

    public void renderScreen(SpriteBatch sb){
        states.peek().renderScreen(sb);
    }
}
