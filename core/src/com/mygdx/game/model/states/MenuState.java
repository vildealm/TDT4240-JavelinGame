package com.mygdx.game.model.states;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.Screen2;

public class MenuState extends State {
    private BitmapFont font;
    private Screen2 currentScreen;

    public MenuState(GameStateManager gsm){
        super(gsm);

        font = new BitmapFont();
        currentScreen = ScreenFactory.getScreen("MENU");
        renderScreen();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#MENU", String.valueOf(gsm));
    }

    @Override
    public void handleInput() {


        if(Gdx.input.justTouched()){
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
            Gdx.app.log("#MENU1", String.valueOf(gsm.getStates()));
            gsm.set(new GameState(gsm));
            //currentScreen = ScreenFactory.getScreen("PLAY");
            //renderScreen();
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
            Gdx.app.log("#MENU2", String.valueOf(gsm.getStates()));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void renderBatch(SpriteBatch sb) {

    }


    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#MENU", String.valueOf(currentScreen));
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#MENU", String.valueOf(gsm.getStates()));
    }

    public void dispose(){

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
