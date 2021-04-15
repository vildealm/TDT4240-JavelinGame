package com.mygdx.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.SetupState;


public class MenuScreen implements Screen2 {

    private BitmapFont font;
    private Stage stage;
    private Javelin javelin;
    private TextButton playButton;
    private TextButton.TextButtonStyle playButtonStyle;
    private Skin skin;
    private GameStateManager gsm;
    Sprite setupSprite;


    public MenuScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        javelin = new Javelin();
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        skin = new Skin();
        setupSprite = new Sprite(Assets.getTexture(Assets.setupBackground));
        Gdx.input.setInputProcessor(stage);
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = font;
        //stage.addActor(javelin);
        //stage.addTouchFocus(new InputListener(), javelin, javelin, 1,1);
        font = new BitmapFont();
        TextButton playButton = new TextButton("PLAY", playButtonStyle);
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        playButton.getLabel().setFontScale(5, 5);
        stage.addActor(playButton);

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("ButtonGameState", String.valueOf(gsm));


        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
               gsm.set(new SetupState(gsm));
            }
        });


        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);

    }

    public void show() {

    }

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "MENU", 70, 100);
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        sb.draw(setupSprite, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
