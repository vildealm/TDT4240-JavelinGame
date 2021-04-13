package com.mygdx.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;


public class MenuScreen implements Screen2 {

    //private Stage stage;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    //private Texture playBtn;
    private BitmapFont font;
    private Stage stage;
    private Javelin javelin;
    private TextButton playButton;
    private TextButton.TextButtonStyle playButtonStyle;
    private Skin skin;
    private GameStateManager gsm;


    public MenuScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        javelin = new Javelin();
        font = new BitmapFont();
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        skin = new Skin();
        Gdx.input.setInputProcessor(stage);
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = font;
        stage.addActor(javelin);
        stage.addTouchFocus(new InputListener(), javelin, javelin, 1,1);
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

               gsm.set(new GameState(gsm));
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
