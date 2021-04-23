package com.mygdx.game.model.states;

import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class HighscoreState extends State{

    Screen2 currentScreen;

    public HighscoreState(GameStateManager gsm){
        super(gsm);
        currentScreen = ScreenFactory.getScreen("HIGHSCORE");
        renderScreen();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void renderScreen() {

        gsm.game.setScreen(currentScreen);

    }

    @Override
    public void dispose() {

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
