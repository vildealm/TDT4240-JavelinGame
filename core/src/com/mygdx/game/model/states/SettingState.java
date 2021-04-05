package com.mygdx.game.model.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingState implements State {

    private GameStateManager gsm;
    public SettingState(GameStateManager gsm) {
        this.gsm = gsm;
    }

   /* @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }*/

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
