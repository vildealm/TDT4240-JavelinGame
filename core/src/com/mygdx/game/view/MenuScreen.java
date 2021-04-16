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
import com.badlogic.gdx.scenes.scene2d.InputListener;
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


public class MenuScreen implements Screen2 {

    private BitmapFont font;
    private Stage stage;
    private Button playButton;
    //private TextButton.TextButtonStyle playButtonStyle;
    //private Skin skin;
    private GameStateManager gsm;
    private TextureAtlas buttonAtlas;
    private Texture buttonImage;
    private Sprite background;


    public MenuScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());
        buttonImage = new Texture("button.png");
        font = new BitmapFont();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));
        //skin = new Skin(Gdx.files.internal("uiskin.json"));
        //buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        //skin.addRegions(buttonAtlas);
        //playButtonStyle = new TextButton.TextButtonStyle();
        //playButtonStyle.font = font;
        //playButtonStyle.up = skin.getDrawable("up-button");
        //playButtonStyle.down = skin.getDrawable("down-button");
        //playButtonStyle.checked = skin.getDrawable("checked-button");
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(buttonImage)));
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        playButton.setHeight(200);
        playButton.setWidth(500);
        stage.addActor(playButton);



        Gdx.input.setInputProcessor(stage);




        font = new BitmapFont();



        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("ButtonGameState", String.valueOf(gsm));


        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
               gsm.set(new GameState(gsm)); //skal være SetupState
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
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
