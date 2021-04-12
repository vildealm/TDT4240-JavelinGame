package com.mygdx.game.model.states;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class GameState extends State{

    BitmapFont font;
    private Screen2 currentScreen;


    public GameState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        currentScreen = ScreenFactory.getScreen("PLAY");

    }

    @Override
    public void handleInput() {
/*
        if(Gdx.input.justTouched()){

            gsm.set(new MenuState(gsm));
            //currentScreen = ScreenFactory.getScreen("PLAY");
            //renderScreen();

            dispose();
        }

 */
    }

    @Override
    public void update(float dt) {
        //handleInput();
    }


    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#GAME", String.valueOf(currentScreen));
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#GAME", String.valueOf(gsm.getStates()));
    }

    @Override
    public void dispose() {

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
