package com.mygdx.game.model.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState implements State{

    BitmapFont font;
    private GameStateManager gsm;


    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        font = new BitmapFont();
    }

   /* @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "GAME STATE!" , 90, 90);
        sb.end();
    }

    @Override
    public void dispose() {

    }*/

    @Override
    public void render() {

    }

    @Override
    public void changeState(State state) {

    }

    @Override
    public void updateScreen(String type) {

    }
}
