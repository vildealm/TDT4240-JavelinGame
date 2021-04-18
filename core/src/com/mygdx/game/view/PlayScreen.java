package com.mygdx.game.view;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;


import java.util.Random;

public class PlayScreen implements Screen2 {

    private BitmapFont font;
    //private Stage stage;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    //private Texture playBtn;
    private GameStateManager gsm;
    private TextureAtlas man;
    private TextureAtlas throwMan;
    private Animation runningMan;
    private Animation throwingMan;
    private float elapsedTime = 0f;
    private int posX = 20;
    private int speedX = 0;
    private Animation currentAnim;
    private ShapeRenderer shapeRenderer;
    private double attempt;
    private int random = 1;
    private TextButton runArea;
    private TextButton.TextButtonStyle runAreaStyle;
    private BitmapFont runAreafont;

    private boolean check = false;
    //PLayerController
    private PlayerController playerController;

    //Player
    private Player player;

    Animation javelin1;
    private Javelin javelin2;
    private Stage stage;
    private Sprite playBackground;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    public Vector3 vector;
    private Texture throwButtonImage;
    private Texture pauseButtonImage;

    private Texture javelinTexture = new Texture("javelin.png");
    private Sprite javelinSprite = new Sprite(javelinTexture);

    private double velocity = -10.0;

    private boolean luup = true;


    private Vector2 javelinPosition = new Vector2(550, 50);


    private Vector2 javelinVelocityX = new Vector2();
    private Vector2 javelinVelocityY = new Vector2();

    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    public PlayScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        font = new BitmapFont();
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        playBackground = new Sprite(Assets.getTexture(Assets.playBackground));
        playBackground.setPosition(0,0);
        playBackground.setSize(800, 500);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        shapeRenderer = new ShapeRenderer();

        playerController = new PlayerController();
        playerController.setSpeed(speedX);

        player = new Player();
        player.setScore(0,0);

        camera = new OrthographicCamera(1184, 768);
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );

        throwButtonImage = new Texture("throwButton.png");
        Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        throwButton.setPosition(Gdx.graphics.getWidth()-throwButton.getWidth()-10, Gdx.graphics.getHeight()/7);

        pauseButtonImage = new Texture("pauseButton.png");
        Button pauseButton = new Button(new TextureRegionDrawable(new TextureRegion(pauseButtonImage)));
        pauseButton.setPosition(Gdx.graphics.getWidth()-110, Gdx.graphics.getHeight()-110);
        pauseButton.setHeight(100);
        pauseButton.setWidth(100);

        stage.addActor(throwButton);
        stage.addActor(pauseButton);

        //runArea
        runAreafont = new BitmapFont();
        runAreafont.setColor(Color.BLACK);
        runAreafont.getData().setScale(3);
        runAreaStyle = new TextButton.TextButtonStyle();
        runAreaStyle.font = runAreafont;

        TextButton runArea = new TextButton("", runAreaStyle);
        runArea.setPosition(0,0);
        runArea.setWidth((Gdx.graphics.getWidth()*2)/3);
        runArea.setHeight(Gdx.graphics.getHeight());
        runArea.getLabel().setFontScale(5, 5);
        stage.addActor(runArea);

        //Animations
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation( 0.41f, throwMan.getRegions());
        currentAnim = runningMan;

        runArea.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                playerController.increaseSpeed();
            }
        });

        throwButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                camera.translate(10f, 0f);
                /*Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen", String.valueOf(Gdx.graphics.getHeight()));
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen2", String.valueOf(camera.position));*/
                random=(600-(posX+50));
                player.setScore(playerController.getSpeed(), (600-(posX+50)));
            }
        });

        pauseButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //sett state til pauseState
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen", String.valueOf("pause"));
            }
        });

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
    }

    @Override
    public void render(float delta, SpriteBatch sb) {

        /*camera.update();
        sb.begin();
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        font.draw(sb, "PlayScreen!", 70, 180);

        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );*/
        camera.update();
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
        posX += Gdx.graphics.getDeltaTime() * playerController.getSpeed();
        playerController.reduceSpeed();

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(playBackground, 0,0, 2500, 1000);
        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, true),posX, 20);
        font.draw(sb, "Speed: "+ playerController.getSpeed() + " Dist:"+random+" Score: "+player.getScore(), 310, 600);
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
        font.dispose();
        shapeRenderer.dispose();
    }
}
