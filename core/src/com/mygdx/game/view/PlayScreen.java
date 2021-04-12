package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;

public class PlayScreen implements Screen2 {

    private SpriteBatch batch;
    private BitmapFont font;
    //private Stage stage;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    private TextureAtlas man;
    private Sprite sprite;
    private Animation runningMan;
    private TextureRegion tr;
    //private Texture playBtn;
    private float elapsedTime = 0f;
    private int posX = 20;
    private int speedX = 0;
    private int touchCount = 1;



    public PlayScreen(JavelinGame game){
        super();
        this.game = game;
        batch = new SpriteBatch();
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
        //font = new BitmapFont();
        man = new TextureAtlas(Gdx.files.internal("running-sheets/RunSprites.atlas"));
        tr = man.findRegion("running-sheets/running-1");
        sprite = new Sprite(tr);
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
        runningMan = new Animation(1f/ 20f, man.getRegions());
    }

    public void runningControls(){
        if(speedX > 30){
            speedX--;
        }
        if(Gdx.input.justTouched()){
            touchCount++;
            if(speedX<400){
                speedX = speedX + 20;
            }
        }
        posX += Gdx.graphics.getDeltaTime() * speedX;
    }

    @Override
    public void show() {
        //stage = new Stage(new ScreenViewport());
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("#Playscreen", String.valueOf("Playscreen"));
        runningControls();
        batch.begin();
        //sprite.draw(batch);
        batch.draw((TextureRegion) runningMan.getKeyFrame(elapsedTime, true),posX, 20);
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //font.draw(batch, "PlayScreen!", 70, 180);
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        batch.end();
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
