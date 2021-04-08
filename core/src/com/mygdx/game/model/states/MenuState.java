package com.mygdx.game.model.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;

public class MenuState extends State {
    private BitmapFont font;
    private Screen currentScreen;

    public MenuState(GameStateManager gsm){
        super(gsm);
        font = new BitmapFont();
        currentScreen = ScreenFactory.getScreen("MENU");
    }

    @Override
    public void handleInput() {


        if(Gdx.input.justTouched()){
            gsm.set(new GameState(gsm));

            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void renderScreen(SpriteBatch sb) {
        gsm.game.setScreen(currentScreen);
    }

    public void dispose(){

    }
}
