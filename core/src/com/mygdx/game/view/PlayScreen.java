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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.backend.FirebaseInterface;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.EndState;
import com.mygdx.game.model.states.GameStateManager;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.MenuState;


import java.util.ArrayList;

public class PlayScreen implements Screen2 {

    private PlayerController playerController;
    private FirebaseInterface _FBIC;
    private GameStateManager gsm;
    private Javelin javelin;
    private Player player;
    final ArrayList<Player> players = new ArrayList<>();

    private float elapsedTime = 0f;
    private int posX = 20;
    private int speedX = 0;
    private boolean thrown;
    private int cameraLimit;
    private double deltaTime = 0.3f;
    private boolean normalThrow = true;
    private double prevScore;
    private int round;
    private boolean loop = true;
    private boolean isPaused;

    private BitmapFont font;
    private Stage stage;
    private Sprite playBackground;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private Window pause;
    private TextButton.TextButtonStyle runAreaStyle;
    private BitmapFont runAreafont;

    //textures and animations
    private Texture throwButtonImage;
    private Texture nextThrowImage;
    private Texture pauseButtonImage;
    private Texture finishGameImage;
    private Texture resumeButtonImage;
    private Texture backgroundPauseImage;
    private Texture quitButtonImage;
    private Texture nextPlayerImage;
    private Texture currentNextImage;
    private TextureAtlas manImageAtlas;
    private TextureAtlas throwManAtlas;
    private Animation runningManAnimation;
    private Animation throwingManAnimation;
    private Animation currentAnim;

    public PlayScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        font = new BitmapFont();
        javelin = new Javelin();
        backgroundPauseImage = Assets.getTexture(Assets.pauseBackground);
        pauseButtonImage = Assets.getTexture(Assets.pauseButton);
        resumeButtonImage = Assets.getTexture(Assets.resumeButton);
        nextThrowImage = Assets.getTexture(Assets.newxtThrowButton);
        nextPlayerImage = Assets.getTexture(Assets.nextPlayer);

        TextureRegionDrawable pauseDrawable = new TextureRegionDrawable(new TextureRegion(backgroundPauseImage));
        Window.WindowStyle windowstyle = new Window.WindowStyle();
        windowstyle.titleFont = font;
        windowstyle.background = pauseDrawable;

        if (gsm.getGameRules().getPlayers().size() > 1){
            currentNextImage = nextPlayerImage;
        }
        else{
            currentNextImage = nextThrowImage;
        }


        //Pause window
        this.pause = new Window("",windowstyle);
        quitButtonImage = Assets.getTexture(Assets.QuitButton);
        Button quitButton1 = new Button(new TextureRegionDrawable(new TextureRegion(quitButtonImage)));
        quitButton1.setPosition(20,20);
        pause.add(quitButton1);

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
        manImageAtlas = new TextureAtlas(Gdx.files.internal("newSprites/newRunAtlas.atlas"));
        throwManAtlas = new TextureAtlas(Gdx.files.internal("newSprites/throw.atlas"));
        runningManAnimation = new Animation(3f/ 20f, manImageAtlas.getRegions());
        throwingManAnimation = new Animation( 0.41f, throwManAtlas.getRegions());
        currentAnim = runningManAnimation;
    }

    public void addButtons(){
        throwButtonImage = Assets.getTexture(Assets.throwButton);
        finishGameImage = Assets.getTexture(Assets.goToScoreButton);
        pauseButtonImage = Assets.getTexture(Assets.pauseButton);

        final Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        final Button nextThrowButton = new Button(new TextureRegionDrawable(new TextureRegion(currentNextImage)));
        final Button finishGameButton = new Button(new TextureRegionDrawable(new TextureRegion(finishGameImage)));
        Button pauseButton = new Button(new TextureRegionDrawable(new TextureRegion(pauseButtonImage)));

        throwButton.setPosition(Gdx.graphics.getWidth() -throwButton.getWidth()-10, Gdx.graphics.getHeight()/7);
        nextThrowButton.setPosition(Gdx.graphics.getWidth() -550, 30);
        finishGameButton.setPosition(Gdx.graphics.getWidth() -500, 20 );

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
                currentAnim = throwingManAnimation;
                loop = false;
                posX += Gdx.graphics.getDeltaTime() * speedX;
                javelin.setPositionX(posX);
                prevScore = player.getScore();
                player.calculateScore(playerController.getSpeed(), (680-(posX+50)));
                thrown = true;
                cameraLimit = (int) (player.getScore()*30);

                playerController.setSpeed(0);
                if (posX < 300) {
                    normalThrow = false;
                }
                throwButton.remove();
                if(round == (players.size()*2)){
                    stage.addActor(finishGameButton);
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

    @Override
    public void show() {
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        if(isPaused){
            deltaTime = 0;
        }
        javelin.setSpriteRotation(30);

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
            javelin.getJavelinSprite().setPosition(javelin.updateJavelinPosition(normalThrow, posX, cameraLimit, deltaTime).x, javelin.updateJavelinPosition(normalThrow, posX, cameraLimit, deltaTime).y);
            javelin.getJavelinSprite().draw(sb);
        }

        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, loop), posX, 20);
        javelin.landedJavelin(camera);
        font.draw(sb, "Player: "+ player.getUsername() + " Country: "+ player.getCountry() + " Score: "+player.getScore()+" Round: "+round, camera.position.x-500, 600);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void checkScore(){

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
        currentAnim = runningManAnimation;
        loop = true;
        thrown = false;
        normalThrow = true;
        javelin.setVelocity( -17.0);
        javelin.setSpriteRotation(30);
        javelin.setPositionX(posX);
        javelin.setPositionY(55);
        addButtons();
        camera.position.set((float)((Gdx.graphics.getWidth()/2)), (float) ((Gdx.graphics.getHeight()/2)*0.9), 0 );
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