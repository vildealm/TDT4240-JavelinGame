package com.mygdx.game.model.states;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class SetupState extends State {

    private Screen2 currentScreen;

    /***
     * All methods in this class is described in the State interface
     * This state renderes the Setupscreen and changes when the PLAY buttons is clicked.
     * @param gsm: Game state manager
     */
    public SetupState(GameStateManager gsm){
        super(gsm);
        currentScreen = ScreenFactory.getScreen("SETUP");
        renderScreen();
    }

    @Override
    public void update(float dt) {}

    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }

    public void dispose() { }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
