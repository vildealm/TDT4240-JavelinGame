package com.mygdx.game.model.states;
import com.mygdx.game.view.Screen2;

public abstract class State {
    public GameStateManager gsm;

    /***
     * Initializes the GameStateManager to the JavelinGame
     * @param gsm: GameStateManager.
     */
    public State(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update(float dt);

    public abstract void renderScreen();

    public abstract void dispose();

    /***
     * Returns the currentScreen that is shown.
     */
    public abstract Screen2 getScreen();

}


