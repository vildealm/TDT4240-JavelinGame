package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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
    private TextureAtlas throwMan;
    private Animation runningMan;
    private Animation throwingMan;
    //private Texture playBtn;
    private float elapsedTime = 0f;
    private int posX = 20;
    private int speedX = 0;
    private Animation currentAnim;
    private ShapeRenderer shapeRenderer;
    private double attempt;
    private int counter;
    private Texture javelin;


    Animation javelin1;

    private Vector2 javelinPosition = new Vector2();
    private Vector2 javelinVelocity = new Vector2();
    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    public PlayScreen(JavelinGame game){
        super();
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        shapeRenderer = new ShapeRenderer();
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation(5f/20f, throwMan.getRegions());
        currentAnim = runningMan;
        javelin = new Texture("javelin.png");
    }

    public void runningControls(){
      

        if(speedX > 30){
            speedX--;
        }
        if(Gdx.input.justTouched()){
            counter++;
            if(speedX<400){
                speedX = speedX + 20;
            }
        }
        posX += Gdx.graphics.getDeltaTime() * speedX;
        if(posX > 400){
            currentAnim = throwingMan;
            Gdx.app.setLogLevel(Application.LOG_DEBUG);        }

        Gdx.app.log("#TapCount", String.valueOf(speedX));

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
        if(posX<600){
            runningControls();
        }
        else{
            attempt = calculatePoints(counter, 1);
        }
        batch.begin();

        batch.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, true),posX, 20);
        //batch.draw((TextureRegion) throwingMan.getKeyFrame(elapsedTime, true),posX, 400);
        font.draw(batch, "Score: "+attempt, 700, 200);
        batch.draw(javelin, 580,40);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.line(600, 0, 600, 100);
        shapeRenderer.end();

        //stage.draw();
    }

    public double calculatePoints(double taps, double dist) {
        return (taps / dist) * 3;
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
