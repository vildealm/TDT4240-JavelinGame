package com.mygdx.game.model.states;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
