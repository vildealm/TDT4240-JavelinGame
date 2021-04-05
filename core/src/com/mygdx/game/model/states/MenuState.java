package com.mygdx.game.model.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.controller.ScreenFactory;

public class MenuState implements State {
    private BitmapFont font;
    private GameStateManager gsm;
    private Screen currentScreen;
    TextButton startButton;
    TextButton.TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
    Stage stage;


    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = ScreenFactory.getScreen("MENU");
        renderScreen();
        /*stage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("../android/assetsHeliSprites.png");
        skin.addRegions(buttonAtlas);
        textButtonStyle.fontColor = new Color(0f, 1f, 1f, 1f);
        textButtonStyle.font = font;
        //textButtonStyle.up = skin.getDrawable("up-button");
        //textButtonStyle.down = skin.getDrawable("down-button");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
        stage.addActor(startButton);

        startButton = new TextButton("START GAME", textButtonStyle);

        startButton.addListener(new ClickListener(){

            public void clicked(InputEvent event, Actor actor)
            {
                System.out.println("You clicked me!");
            }
        });*/
    }


    @Override
    public void renderScreen() {
        gsm.game.setScreen(currentScreen);
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }

    @Override
    public void updateScreen(String type) {
        currentScreen = ScreenFactory.getScreen(type);
        renderScreen();
    }

    @Override
    public boolean shouldChangeState(String type) {
        return false;
    }
}
/*
    @Override
    public void handleInput() {
        if(startButton.isPressed()){
            gsm.set(new GameState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        startButton.draw(sb,1 );
        sb.end();
    }

    public void dispose() {
    }
/*

public class MenuState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;

    MenuState(GameStateManager gsm){
        this.gsm = gsm;
        currentScreen = ScreenFactory.getScreen("MENU");
        render();
    }

}
*/