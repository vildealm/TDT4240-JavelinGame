package com.mygdx.game.model.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.JavelinGame;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;
    public JavelinGame game;

    public GameStateManager(JavelinGame game){
        states = new Stack<State>();
        this.game = game;
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void changeState(State state){
        pop();
        states.push(state);
    }

    public void updateScreen(String type){
        states.peek().updateScreen(type);
    }

    /*public void render(SpriteBatch sb){
        states.peek().render(sb);
    }*/
}
