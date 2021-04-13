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
    }

    @Override
    public void handleInput() {

/*
        if(Gdx.input.justTouched()){
            gsm.set(new GameState(gsm));
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
    }

    public void dispose(){

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
