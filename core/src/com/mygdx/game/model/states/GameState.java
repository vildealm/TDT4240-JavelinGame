package com.mygdx.game.model.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State{

    BitmapFont font;


    public GameState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void renderScreen(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "GAME STATE!" , 90, 90);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
