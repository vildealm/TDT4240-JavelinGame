package com.mygdx.game.model.states;

import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class LearnState extends State {

    Screen2 currentScreen;

    public LearnState(GameStateManager gsm){
        super(gsm);
        currentScreen = ScreenFactory.getScreen("LEARN");
        renderScreen();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void renderScreen() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
