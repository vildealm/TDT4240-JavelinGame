package com.mygdx.game.model.states;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.view.Screen2;

public class MenuState extends State implements Screen2 {
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



    }

    @Override
    public void update(float dt) {
        //handleInput();
    }



    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose(){

    }

    @Override
    public Screen2 getScreen() {
        return currentScreen;
    }
}
