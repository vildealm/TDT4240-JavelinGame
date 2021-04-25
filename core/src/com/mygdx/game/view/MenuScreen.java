package com.mygdx.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.GameRules;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.LearnState;
import com.mygdx.game.model.states.MultiplayerSelectionState;
import com.mygdx.game.model.states.SetupState;

import java.util.ArrayList;


public class MenuScreen implements Screen2 {

    private GameStateManager gsm;
    private Stage stage;
    private Sprite background;
    private Texture playButtonImage;
    private Texture learnButtonImage;
    private Button playButton;
    private Button learnButton;

    public MenuScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Sprite(Assets.getTexture(Assets.setupBackground));

        //buttons
        playButtonImage = Assets.getTexture(Assets.playButton);
        learnButtonImage = Assets.getTexture(Assets.learnButton);
        playButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonImage)));
        learnButton = new Button(new TextureRegionDrawable(new TextureRegion(learnButtonImage)));
        playButton.setPosition((Gdx.graphics.getWidth()/2)-(playButton.getWidth()/2), (Gdx.graphics.getHeight()/2)-(playButton.getHeight())/2);
        learnButton.setWidth(300);
        learnButton.setHeight(80);
        learnButton.setPosition((Gdx.graphics.getWidth()/2)-(learnButton.getWidth()/2) , (Gdx.graphics.getHeight()/2)-(learnButton.getHeight())/2 -200);

        stage.addActor(playButton);
        stage.addActor(learnButton);

        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
               gsm.set(new MultiplayerSelectionState(gsm));
            }
        });

        learnButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new LearnState(gsm));
            }
        });
    }

    public void show() { }

    public void render(float delta, SpriteBatch sb) {
        Matrix4 mat = new Matrix4();
        mat.setToOrtho2D(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setProjectionMatrix(mat);

        sb.begin();
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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

    }
}
