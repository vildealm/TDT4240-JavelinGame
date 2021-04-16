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
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameStateManager;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class PlayScreen implements Screen2 {

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
    private int random;
    private double deltaTime = 0.2f;

    private boolean check = false;

    private Stage stage;

    private Texture javelinTexture = new Texture("javelin.png");
    private Sprite javelinSprite = new Sprite(javelinTexture);

    private double velocity = -10.0;

    private boolean luup = true;


    private Vector2 javelinPosition = new Vector2(550, 50);


    private Vector2 javelinVelocityX = new Vector2();
    private Vector2 javelinVelocityY = new Vector2();

    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    public PlayScreen(GameStateManager gsm){
        super();
        this.game = game;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        shapeRenderer = new ShapeRenderer();
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);

        //Player
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation( 0.41f, throwMan.getRegions());
        currentAnim = runningMan;

        //Score
        Random rand = new Random();
        int upperbound = 10;
        random = rand.nextInt(upperbound);

        javelinSprite.setRotation(20);

        //fra Setting_Screen
        //background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
        //javelin2 = new Javelin();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //stage.addActor(javelin2);
        //stage.addTouchFocus(new InputListener(), javelin2, javelin2, 1,1);
    }

    //Player
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
        if(posX > 300){
            luup = false;
            elapsedTime =1;
            currentAnim = throwingMan;


        }
    }

    public boolean landedJavelin(){
        if (javelinPosition.y < 12 && javelinPosition.x > 800 ){
            velocity = 0;
        }
        return true;
    }

    public Vector2 updateJavelinPosition(){
        javelinPosition.x = (float) (javelinPosition.x - (velocity * deltaTime));
        javelinPosition.y = (float) (javelinPosition.y - (velocity * deltaTime));

        if(javelinPosition.x > 700) {
            javelinSprite.setRotation(0);
            javelinPosition.y = (float) (javelinPosition.y - (-velocity * deltaTime));
        }
        if(javelinPosition.x > 900){
            javelinPosition.y = (float) (javelinPosition.y - (-velocity * deltaTime));
            javelinSprite.setRotation(-20);

        }

        return javelinPosition;
    }

    @Override
    public void show() {
        //stage = new Stage(new ScreenViewport());
        //Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta, SpriteBatch sb) {

        elapsedTime += Gdx.graphics.getDeltaTime();
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("#Playscreen", String.valueOf("Playscreen"));

        //Player
        if(posX<545){
            runningControls();
        }

        else{
            attempt = (double)Math.round(calculatePoints(speedX, random) * 100d) / 100d;
            check = true;
        }

        sb.begin();
        if(check==true){
            javelinSprite.setPosition(updateJavelinPosition().x , updateJavelinPosition().y);
            javelinSprite.draw(sb);

        }
        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, luup), posX, 20);
        if(javelinPosition.y < 10){
            font.draw(sb, "Speed: "+ speedX + " Dist:"+random+" Score: "+ attempt, 500, 600);
        }
        landedJavelin();
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //font.draw(sb, "PlayScreen!", 70, 180);
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        sb.end();

        //Makes line, showing where to throw
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.line(600, 0, 600, 100);
        shapeRenderer.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    //Score
    public double calculatePoints(double speed, double dist){
        return (5*(speed/10)*(15-dist))/15;
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
