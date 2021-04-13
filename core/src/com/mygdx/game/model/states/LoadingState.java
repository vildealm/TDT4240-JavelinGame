package com.mygdx.game.model.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class LoadingState extends State {

    private GameStateManager gsm;
    private Screen2 currentScreen;

    public LoadingState(GameStateManager gsm) {
        super(gsm);
        currentScreen = ScreenFactory.getScreen("SETTING");
        renderScreen();
    }

    @Override
    public void handleInput() { }

    @Override
    public void update(float dt) { }

    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }

    @Override
    public void dispose() { }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
