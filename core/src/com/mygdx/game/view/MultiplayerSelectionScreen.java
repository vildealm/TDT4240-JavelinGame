package com.mygdx.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.SetupState;


public class MultiplayerSelectionScreen implements Screen2 {

    private BitmapFont font;
    private Stage stage;
    private GameStateManager gsm;
    private TextureAtlas buttonAtlas;
    private Button onePlayerButton;
    private Button twoPlayerButton;
    private Button threePlayerButton;
    private Button fourPlayerButton;

    private Texture onePlayerButtonImage;
    private Texture twoPlayerButtonImage;
    private Texture threePlayerButtonImage;
    private Texture fourPlayerButtonImage;

    private Sprite background;


    public MultiplayerSelectionScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());

        onePlayerButtonImage = Assets.getTexture(Assets.onePlayerButton);
        twoPlayerButtonImage = Assets.getTexture(Assets.twoPlayerButton);
        threePlayerButtonImage = Assets.getTexture(Assets.threePlayerButton);
        fourPlayerButtonImage = Assets.getTexture(Assets.fourPlayerButton);

        font = new BitmapFont();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));


        //Multiplayer buttons
        onePlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(onePlayerButtonImage)));
        twoPlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(twoPlayerButtonImage)));
        threePlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(threePlayerButtonImage)));
        fourPlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(fourPlayerButtonImage)));

        onePlayerButton.setPosition(300,350);
        twoPlayerButton.setPosition(700,350);
        threePlayerButton.setPosition(1100,350);
        fourPlayerButton.setPosition(1500,350);


        stage.addActor(onePlayerButton);
        stage.addActor(twoPlayerButton);
        stage.addActor(threePlayerButton);
        stage.addActor(fourPlayerButton);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("ButtonGameState", String.valueOf(gsm));


        onePlayerButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new SetupState(gsm)); //skal være SetupState
            }
        });

        twoPlayerButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new SetupState(gsm)); //skal være SetupState
            }
        });

        threePlayerButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new SetupState(gsm)); //skal være SetupState
            }
        });

        fourPlayerButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new SetupState(gsm)); //skal være SetupState
            }
        });

    }

    public void show() { }

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "MENU", 70, 100);
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

