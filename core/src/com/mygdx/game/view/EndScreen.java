package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.HighscoreState;

import java.awt.Font;
import java.util.ArrayList;

public class EndScreen implements Screen2 {
    private Texture HSbuttonTexture;
    private ArrayList<Player> players;
    private GameStateManager gsm;

    private BitmapFont getHighscoreFont;
    private TextButton.TextButtonStyle getHighscoreFontStyle;

    private Stage stage;
    private Viewport viewport;
    private Texture highscoreImage;

    private BitmapFont font;

    public EndScreen(final GameStateManager gsm) {

        super();
        this.gsm = gsm;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        stage = new Stage(new ScreenViewport());

        highscoreImage = Assets.getTexture(Assets.HighscoreButton);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

        Button highscoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highscoreImage)));
        highscoreButton.setPosition(950, 650);
        highscoreButton.setHeight(90);
        highscoreButton.setWidth(300);

        stage.addActor(highscoreButton);
        Gdx.input.setInputProcessor(stage);

        highscoreButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new HighscoreState(gsm));
            }
        });

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {

        sb.begin();
        font.draw(sb,  "Your scores: ", Gdx.graphics.getWidth()/2 - 90, Gdx.graphics.getHeight()/2 );
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


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
        stage.dispose();
        getHighscoreFont.dispose();

    }
}
