package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.sun.org.apache.xpath.internal.operations.Or;

public class PlayScreen implements Screen2 {

    private BitmapFont font;
    //private Stage stage;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    //private Texture playBtn;
    private Stage stage;
    private Sprite playBackground;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    public Vector3 vector;
    private Texture buttonImage;


    public PlayScreen(final GameStateManager gsm){
        super();
        this.game = game;
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
        font = new BitmapFont();
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        //viewport.setScreenSize(800, 480);
        Gdx.input.setInputProcessor(stage);
        playBackground = new Sprite(Assets.getTexture(Assets.playBackground));
        playBackground.setPosition(0,0);
        playBackground.setSize(800, 500);
        //playBackground.setSize(800, 480);
        //playBackground.setBounds(0, 0, 2500, 400);
        //playBackground.setBounds(0,0, playBackground.getWidth(), playBackground.getHeight());


        //float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        camera = new OrthographicCamera(1184, 768);
        //camera.setToOrtho(false, 800, 480);

        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );
        vector = new Vector3();
        //stage.addActor(playBackground);
        //vector.x+=100.0f * Gdx.graphics.getDeltaTime();
        //vector.y+=100.0f * Gdx.graphics.getDeltaTime();
        buttonImage = new Texture("button.png");
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(buttonImage)));
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        playButton.setHeight(200);
        playButton.setWidth(500);
        stage.addActor(playButton);

        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                camera.translate(10f, 0f);
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen", String.valueOf(Gdx.graphics.getHeight()));
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen2", String.valueOf(camera.position));
            }
        });

    }
    @Override
    public void show() {
        //stage = new Stage(new ScreenViewport());
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        camera.update();
        sb.begin();
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        font.draw(sb, "PlayScreen!", 70, 180);
        sb.setProjectionMatrix(camera.combined);
        sb.draw(playBackground, 0,0, 2500, 1000);
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
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

    }
}
