package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.State;


public class SetupScreen implements Screen2{

    //Stage ;
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private Javelin javelin;


    //Background
    Sprite setupSprite;
    private Sprite sprite;
    private Texture container;

    //Buttons
    private Texture playBtn;
    private Texture addPlayer;

    public SetupScreen(GameStateManager gsm){
        super();
        //Assets.load();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#SetupSceen", String.valueOf(Assets.getTexture(Assets.setupBackground)));
        setupSprite = new Sprite(Assets.getTexture(Assets.setupBackground));
        //setupBackground =  new Texture("textures/backgrounds/mainBackground.png");
        //sprite = new Sprite(setupBackground);
        javelin = new Javelin();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(javelin);
        stage.addTouchFocus(new InputListener(), javelin, javelin, 1,1);
        font = new BitmapFont();
    }

    public void show() {}

    public void render(float delta, SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        font.draw(sb, "SETUP", 70, 100);
        sb.draw(setupSprite, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        stage.dispose();

    }

}
