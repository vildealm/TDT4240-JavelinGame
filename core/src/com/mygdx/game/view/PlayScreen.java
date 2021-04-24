package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.backend.FirebaseInterface;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.EndState;
import com.mygdx.game.model.states.GameStateManager;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.MenuState;
import com.mygdx.game.model.states.MultiplayerSelectionState;


import java.util.ArrayList;

public class PlayScreen implements Screen2 {

    private BitmapFont font;
    private JavelinGame game;
    private Texture gameName;
    private Texture background;
    private GameStateManager gsm;
    private TextureAtlas man;
    private TextureAtlas throwMan;
    private Animation runningMan;
    private Animation throwingMan;
    private float elapsedTime = 0f;
    private int posX = 20;
    private int speedX = 0;
    private Animation currentAnim;
    private TextButton runArea;
    private TextButton.TextButtonStyle runAreaStyle;
    private BitmapFont runAreafont;
    private boolean thrown;
    private int cameraLimit;
    private double deltaTime = 0.3f;

    private boolean normalThrow = true;
    private double prevScore;
    //PLayerController
    private PlayerController playerController;
    private boolean showScore;

    private FirebaseInterface _FBIC;

    //Player
    private Player player;
    private int round;

    private Stage stage;
    private Stage stage2;
    private Sprite playBackground;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private Texture throwButtonImage;
    private Texture nextPlayerButton;

    private Texture nextThrowImage;
    private Texture pauseButtonImage;
    private Texture finishGameImage;
    private Texture resumeButtonImage;
    private Texture standingMan;
    private Texture backgroundPauseImage;
    private Texture quitButton;
    private Texture nextPlayerImage;
    private Texture currentNextImage;


    private Sprite javelinSprite = new Sprite(Assets.getTexture(Assets.javelin));

    private double velocity = -17.0;
    private boolean loop = true;

    private Vector2 javelinPosition = new Vector2(550, 55);


    private Window pause;

    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    final ArrayList<Player> players = new ArrayList<>();

    private boolean isPaused;

    public PlayScreen(final GameStateManager gsm){

        super();
        this.gsm = gsm;
        font = new BitmapFont();

        viewport = new ScreenViewport();
        backgroundPauseImage = Assets.getTexture(Assets.pauseBackground);
        pauseButtonImage = Assets.getTexture(Assets.pauseButton);
        resumeButtonImage = Assets.getTexture(Assets.resumeButton);
        nextPlayerButton = Assets.getTexture(Assets.nextPlayer);
        TextureRegionDrawable pauseDrawable = new TextureRegionDrawable(new TextureRegion(backgroundPauseImage));
        Window.WindowStyle windowstyle = new Window.WindowStyle();
        windowstyle.titleFont = font;
        windowstyle.background = pauseDrawable;

        nextThrowImage = Assets.getTexture(Assets.newxtThrowButton);
        nextPlayerImage = Assets.getTexture(Assets.nextPlayer);


        if (gsm.getGameRules().getPlayers().size() > 1){
            currentNextImage = nextPlayerImage;
        }
        else{
            currentNextImage = nextThrowImage;
        }

        stage = new Stage(viewport);
        stage2 = new Stage(viewport);
        this.pause = new Window("",windowstyle);

        quitButton = Assets.getTexture(Assets.QuitButton);
        Button quitButton1 = new Button(new TextureRegionDrawable(new TextureRegion(quitButton)));
        quitButton1.setPosition(20,20);
        pause.add(quitButton1);
        //pause.findActor(String.valueOf(quitButton1)).setPosition(50,200);
       // quitButton1.setSize(300, 400);

        quitButton1.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.getGameRules().clearPlayers();
                gsm.set(new MenuState(gsm));
            }
        });

        Button continueButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeButtonImage)));
        pause.add(continueButton);
        continueButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                pause.remove();
            }
        });

        //pause.padTop(64);
        pause.setSize(stage.getWidth()-30,stage.getHeight()-10);
        pause.setPosition(stage.getWidth()/2-pause.getWidth()/2, stage.getHeight()/2-pause.getHeight()/2);
        pause.setMovable(false);

        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);

        this._FBIC = gsm.game.getFirebaseInterface();
        _FBIC.initUser();
        playBackground = new Sprite(Assets.getTexture(Assets.playBackground));
        playBackground.setPosition(0,0);
        playBackground.setSize(Gdx.graphics.getWidth(), (float) (Gdx.graphics.getHeight()));
        cameraLimit = 0;
        round = 1;
        thrown = false;
        isPaused = false;

        addButtons();

        for (Player player : gsm.getGameRules().getPlayers()){
            players.add(player);
            player.setScore(0);
        }

        player = players.get(round-1);
        playerController = new PlayerController();
        playerController.setSpeed(speedX);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), (float) (Gdx.graphics.getHeight()*0.9));
        camera.position.set((float)((Gdx.graphics.getWidth()/2)), (float) ((Gdx.graphics.getHeight()/2)*0.9), 0 );

        //Animations

        man = new TextureAtlas(Gdx.files.internal("tttt/newRunAtlas.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("tttt/throw.atlas"));
        //standingMan = new Texture(Gdx.files.internal("runningscreenshots/pixl-frame-0.png"));
        //currentAnim = standingMan;
        runningMan = new Animation(3f/ 20f, man.getRegions());
        throwingMan = new Animation( 0.41f, throwMan.getRegions());
        currentAnim = runningMan;

    }
    public void addButtons(){
        throwButtonImage = Assets.getTexture(Assets.throwButton);
        final Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        throwButton.setPosition(Gdx.graphics.getWidth() -throwButton.getWidth()-10, Gdx.graphics.getHeight()/7);





        final Button nextThrowButton = new Button(new TextureRegionDrawable(new TextureRegion(currentNextImage)));

        nextThrowButton.setPosition(Gdx.graphics.getWidth() -550, 30);

        finishGameImage = Assets.getTexture(Assets.goToScoreButton);
        final Button finishGameButton = new Button(new TextureRegionDrawable(new TextureRegion(finishGameImage)));
        finishGameButton.setPosition(Gdx.graphics.getWidth() -500, 20 );

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
                currentAnim = throwingMan;
                loop = false;
                posX += Gdx.graphics.getDeltaTime() * speedX;
                javelinPosition.x = posX;
                prevScore = player.getScore();
                player.calculateScore(playerController.getSpeed(), (680-(posX+50)));
                thrown = true;

                playerController.setSpeed(0);
                if (posX < 300) {
                    normalThrow = false;
                }
                throwButton.remove();
                if(round == (players.size()*2)){
                    if(showScore = true) {
                        stage.addActor(finishGameButton);
                    }
                }
                else{

                    stage.addActor(nextThrowButton);
                }
            }
        });

        nextThrowButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                reset();
                nextThrowButton.remove();
            }
        });


        finishGameButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0 );
                checkScore();
                gsm.getGameRules().setPlayers(players);
                for(Player player : players){
                    _FBIC.setValueInDb(player.getUsername(), player.getScore(), player.getCountry());
                }
                dispose();
                gsm.set(new EndState(gsm));
            }
        });

        pauseButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(pause);
            }
        });
    }

    public void landedJavelin(){
        if (javelinPosition.y < 12 && javelinPosition.x > camera.position.x-600  ){
            velocity = 0;
            showScore = true;
        }
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
        if(isPaused){
            deltaTime = 0;
        }
        javelinSprite.setRotation(30);

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

        if(thrown) {
            javelinSprite.setPosition(updateJavelinPosition(normalThrow).x, updateJavelinPosition(normalThrow).y);
            javelinSprite.draw(sb);
        }

        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, loop), posX, 20);
        landedJavelin();
        font.draw(sb, "Player: "+ player.getUsername() + " Country: "+ player.getCountry() + " Score: "+player.getScore()+" Round: "+round, camera.position.x-500, 600);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void checkScore(){
        //Check which score is best
        if(prevScore > player.getScore()){
            player.setScore(prevScore);
        }
    }

    public void reset(){
        checkScore();
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
        loop = true;
        thrown = false;
        normalThrow = true;
        velocity = -17.0;
        javelinSprite.setRotation(30);
        javelinPosition.x = posX;
        javelinPosition.y = 55;
        addButtons();
        camera.position.set((float)((Gdx.graphics.getWidth()/2)), (float) ((Gdx.graphics.getHeight()/2)*0.9), 0 );
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        /*skin = new Skin();
        table = new Table();
        table.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Window pause = new Window("PAUSE", skin);
        pause.padTop(64);
        pause.add(new TextButton("continue", skin));
        pause.pack();
        stage.addActor(pause);*/
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
