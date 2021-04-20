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
import com.mygdx.game.model.states.EndState;
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


import java.util.ArrayList;
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
    private int distance = 1;
    private TextButton runArea;
    private TextButton.TextButtonStyle runAreaStyle;
    private BitmapFont runAreafont;
    private boolean thrown;
    private int cameraLimit;
    private double deltaTime = 0.3f;

    private boolean throwIt = false;
    private boolean normalThrow = true;
    //PLayerController
    private PlayerController playerController;

    //Player
    private Player player;
    private int round;

    private Javelin javelin2;
    private Stage stage;
    private Sprite playBackground;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private Texture throwButtonImage;
    private Texture nextThrowImage;
    private Texture pauseButtonImage;

    private Sprite javelinSprite = new Sprite(Assets.getTexture(Assets.javelin));

    private double velocity = -17.0;
    private boolean loop = true;

    private Vector2 javelinPosition = new Vector2(550, 55);



    private Vector2 javelinVelocityX = new Vector2();
    private Vector2 javelinVelocityY = new Vector2();

    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    final ArrayList<Player> players = new ArrayList<>();
    final Player player1 = new Player();
    final Player player2 = new Player();
    final Player player3 = new Player();



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
        round = 1;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

        playerController = new PlayerController();
        playerController.setSpeed(speedX);

        //Test while waiting for settings_screen
        player1.setUsername("Ed");
        player1.setCountry("NOR");
        player2.setUsername("Boe");
        player2.setCountry("NOR");
        player3.setUsername("Mat");
        player3.setCountry("NOR");
        if(players.isEmpty()){
            //player1.setScore(0);
            //player2.setScore(0);
            players.add(player1);
            players.add(player2);
            players.add(player3);
        }

        player = players.get(round-1);

        //this.player = player;
        //this.player.setScore(0,0);

        camera = new OrthographicCamera(1184, 768);
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );

        addButtons();

        //Animations
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation( 0.41f, throwMan.getRegions());
        currentAnim = runningMan;

    }

    public void addButtons(){
        throwButtonImage = Assets.getTexture(Assets.throwButton);
        final Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        throwButton.setPosition(Gdx.graphics.getWidth()-throwButton.getWidth()-10, Gdx.graphics.getHeight()/7);

        nextThrowImage = Assets.getTexture(Assets.newxtThrowButton);
        final Button nextThrowButton = new Button(new TextureRegionDrawable(new TextureRegion(nextThrowImage)));
        nextThrowButton.setPosition(700, Gdx.graphics.getHeight()/2);

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
                throwIt = true;
                currentAnim = throwingMan;
                loop = false;
                posX += Gdx.graphics.getDeltaTime() * speedX;
                javelinPosition.x = posX;
                throwIt = true;





                /*Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen", String.valueOf(Gdx.graphics.getHeight()));
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen2", String.valueOf(camera.position));*/
                //player.setScore(playerController.getSpeed(), (600-(posX+50)));
                distance=(680-(posX+50));
                //player.setScore(playerController.getSpeed(), (680-(posX+50)));
                //double score = player.getScore();
                player.calculateScore(playerController.getSpeed(), (680-(posX+50)));
                /*if(score > player.getScore()){
                    player.setScore(score);
                }*/
                distance=(680-(posX+50));
                thrown = true;
                System.out.println("javeling pos x : " + javelinPosition.x);
                System.out.println("is javelin thrown?" +thrown);

                playerController.setSpeed(0);
                if (posX < 300) {
                    normalThrow = false;
                }
                throwButton.remove();
                stage.addActor(nextThrowButton);
            }
        });

        nextThrowButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                reset();
                nextThrowButton.remove();
            }
        });

        pauseButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new EndState(gsm));//sett state til pauseState
                Gdx.app.setLogLevel(Application.LOG_DEBUG);
                Gdx.app.log("#PlayScreen", String.valueOf("pause"));
            }
        });
    }

    public boolean landedJavelin(){
        if (javelinPosition.y < 12 && javelinPosition.x > camera.position.x-600  ){
            velocity = 0;

        }

        return true;
    }

    public Vector2 updateJavelinPosition(boolean normalThrow){
        int upLimit;
        cameraLimit = (int) (player.getScore()*30);
        int downLimit;

        if (normalThrow) {
            upLimit = 800;
            downLimit = cameraLimit - 500;
        }
        else {
            if (posX == 20) {
                upLimit = posX + 240;
                downLimit = posX + 310;
            }
            else {
                upLimit = posX + 300;
                downLimit = posX + 350;
            }

        }

        javelinPosition.x = (float) (javelinPosition.x - (velocity * deltaTime));
        javelinPosition.y = (float) (javelinPosition.y - (velocity * deltaTime));

        if (javelinPosition.x > upLimit) {
            javelinSprite.setRotation(0);
            javelinPosition.y = (float) (javelinPosition.y - (-velocity * deltaTime));
        }
        if ((javelinPosition.x > downLimit) ) {
            javelinPosition.y = (float) (javelinPosition.y - (-velocity * deltaTime));
            javelinSprite.setRotation(-30);
        }

        return javelinPosition;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        javelinSprite.setRotation(30);

        /*camera.update();
        sb.begin();
        //game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        font.draw(sb, "PlayScreen!", 70, 180);

        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );*/
        if(thrown){
            cameraLimit = (int) (player.getScore()*30);
            if(cameraLimit > 10500){ //Max length of background
                cameraLimit = 10500;
            }
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

        sb.draw(playBackground, 0,0, 11000, 1000);

        if(throwIt) {
            javelinSprite.setPosition(updateJavelinPosition(normalThrow).x, updateJavelinPosition(normalThrow).y);
            javelinSprite.draw(sb);
        }


        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, loop), posX, 20);
        landedJavelin();
        font.draw(sb, "Player: "+ player.getUsername() + " Dist:"+distance+" Score: "+player.getScore()+" Round: "+round, camera.position.x-300, 600);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void gameFinished(){
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#Finito", String.valueOf("Finito"));
    }

    public void reset(){

        if(round <= players.size()){
            players.set(round-1, player);
        }
        else{
            players.set((round-1) - players.size(), player);
        }
        round++;
        if(round <= players.size()){
            player = players.get(round-1);
        }
        else{
            player= players.get((round-1) - players.size());
        }

        this.posX = 20;
        this.speedX = 0;
        currentAnim = runningMan;
        throwIt = false;
        loop = true;
        thrown = false;
        normalThrow = true;
        velocity = -17.0;
        javelinSprite.setRotation(30);
        javelinPosition.x = posX;
        addButtons();
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );


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
