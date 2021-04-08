package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class MenuScreen implements Screen2 {

    //private Stage stage;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    //private Texture playBtn;
    private BitmapFont font;


    public MenuScreen(JavelinGame game){
        super();
        this.game = game;
        font = new BitmapFont();
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
    }

    public void show() {
        //stage = new Stage(new ScreenViewport());
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        sb.begin();
        font.draw(sb, "MENU", 70, 100);
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        sb.end();
        //stage.draw();
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

    @Override
    public void dispose() {

    }
}
