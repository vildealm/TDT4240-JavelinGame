package com.mygdx.game.model.states;

import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class LoadingState extends State {

    private Screen2 currentScreen;
    /***
     * All methods in this class is described in the State interface
     * This state renderes the loadingscreen and changes when all assets has been fully loaded.
     * @param gsm: Game state manager
     */
    public LoadingState(GameStateManager gsm) {
        super(gsm);
        currentScreen = ScreenFactory.getScreen("LOADING");
        renderScreen();
    }

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
