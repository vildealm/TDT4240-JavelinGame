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
    private int score = 1;
    private TextButton runArea;
    private TextButton.TextButtonStyle runAreaStyle;
    private BitmapFont runAreafont;
    private boolean thrown;

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
    private Texture throwButtonImage;
    private Texture pauseButtonImage;

    private Vector2 javelinPosition = new Vector2();
    private Vector2 javelinVelocity = new Vector2();
    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();
    private int cameraLimit;

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
        thrown = false;
        cameraLimit = 0;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

        playerController = new PlayerController();
        playerController.setSpeed(speedX);

        player = new Player();
        player.setScore(0,0);

        camera = new OrthographicCamera(1184, 768);
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );

        throwButtonImage = Assets.getTexture(Assets.throwButton);
        Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        throwButton.setPosition(Gdx.graphics.getWidth()-throwButton.getWidth()-10, Gdx.graphics.getHeight()/7);

        pauseButtonImage = Assets.getTexture(Assets.pauseButton);
        Button pauseButton = new Button(new TextureRegionDrawable(new TextureRegion(pauseButtonImage)));
        pauseButton.setPosition(Gdx.graphics.getWidth()-110, Gdx.graphics.getHeight()-110);
        pauseButton.setHeight(100);
        pauseButton.setWidth(100);

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

        stage.addActor(throwButton);
        stage.addActor(pauseButton);
        stage.addActor(runArea);

        //Animations
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation(5f/20f, throwMan.getRegions());
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
                score=(610-(posX+50));
                player.setScore(playerController.getSpeed(), (610-(posX+50)));
                thrown = true;
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
        if(thrown){
            cameraLimit = (int) (player.getScore()*10);
            if(camera.position.x < cameraLimit ){
                camera.translate(10f, 0f);
            }


        }

        camera.update();
        elapsedTime += Gdx.graphics.getDeltaTime();
        posX += Gdx.graphics.getDeltaTime() * playerController.getSpeed();
        playerController.reduceSpeed();

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(playBackground, 0,0, 2500, 1000);
        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, true),posX, 20);
        font.draw(sb, "Speed: "+ playerController.getSpeed() + " Dist:"+score+" Score: "+player.getScore() + " CamX: " +camera.position.x + " Limit: "+ cameraLimit, 0, 600);
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
        font.dispose();
    }
}
