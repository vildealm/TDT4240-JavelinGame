package com.mygdx.game.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.controller.ScreenFactory;

public class MenuState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;

    MenuState(GameStateManager gsm){
        this.gsm = gsm;
        currentScreen = ScreenFactory.getScreen("MENU");
        render();
    }

    @Override
    public void render() {
        gsm.game.setScreen(currentScreen);
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }

    @Override
    public void updateScreen(String type) {
        currentScreen = ScreenFactory.getScreen(type);
        render();
    }

}
