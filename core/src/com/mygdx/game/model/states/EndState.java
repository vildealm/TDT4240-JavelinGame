package com.mygdx.game.model.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndState implements State {

    private GameStateManager gsm;
    public EndState(GameStateManager gsm) {
        this.gsm = gsm;
    }


    @Override
    public void renderScreen() {

    }

    @Override
    public void changeState(State state) {

    }

    @Override
    public void updateScreen(String type) {

    }
    @Override
    public boolean shouldChangeState(String type) {
        return false;
    }

}
