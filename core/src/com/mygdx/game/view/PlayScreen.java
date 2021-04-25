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
import com.mygdx.game.controller.JavelinController;
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
    private JavelinController javelinController;
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

    private BitmapFont font;
    private Stage stage;
    private Sprite playBackground;
    private OrthographicCamera camera;
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
    private Button quitButton;
    private Button continueButton;

    public PlayScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this._FBIC = gsm.game.getFirebaseInterface();
        _FBIC.initUser();

        javelin = new Javelin();
        javelinController = new JavelinController();
        font = new BitmapFont();

        //camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), (float) (Gdx.graphics.getHeight()));
        camera.position.set((float)((Gdx.graphics.getWidth()/2)), (float) ((Gdx.graphics.getHeight()/2)), 0 );
        cameraLimit = 0;

        //backgrounds
        playBackground = new Sprite(Assets.getTexture(Assets.playBackground));
        playBackground.setPosition(0,0);
        playBackground.setSize(Gdx.graphics.getWidth(), (float) (Gdx.graphics.getHeight()));
        backgroundPauseImage = Assets.getTexture(Assets.pauseBackground);

        addButtons(); //add buttons and listeners

        //Pause window
        TextureRegionDrawable pauseDrawable = new TextureRegionDrawable(new TextureRegion(backgroundPauseImage));
        Window.WindowStyle windowstyle = new Window.WindowStyle();
        windowstyle.titleFont = font;
        windowstyle.background = pauseDrawable;
        this.pause = new Window("",windowstyle);
        pause.add(quitButton);
        pause.add(continueButton);
        pause.setSize(stage.getWidth()-30,stage.getHeight()-10);
        pause.setPosition(stage.getWidth()/2-pause.getWidth()/2, stage.getHeight()/2-pause.getHeight()/2);
        pause.setMovable(false);

        //font
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);


        round = 1;
        thrown = false;


        for (Player player : gsm.getGameRules().getPlayers()){
            players.add(player);
            player.setScore(0);
        }

        player = players.get(round-1);
        playerController = new PlayerController();
        playerController.setSpeed(speedX);


        //Animations
        manImageAtlas = new TextureAtlas(Gdx.files.internal("newSprites/newRunAtlas.atlas"));
        throwManAtlas = new TextureAtlas(Gdx.files.internal("newSprites/throw.atlas"));
        runningManAnimation = new Animation(3f/ 20f, manImageAtlas.getRegions());
        throwingManAnimation = new Animation( 0.41f, throwManAtlas.getRegions());
        currentAnim = runningManAnimation;
    }



    @Override
    public void show() {
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        javelin.setSpriteRotation(30);
        camera.update();
        elapsedTime += Gdx.graphics.getDeltaTime();
        posX += Gdx.graphics.getDeltaTime() * playerController.getSpeed();
        playerController.reduceSpeed();


        sb.begin();
        sb.setProjectionMatrix(camera.combined);

        sb.draw(playBackground, 0,0, 11000, Gdx.graphics.getHeight());

        //if the player has thrown, the camera and javelin moves
        if(thrown) {
            cameraLimit = (int) (player.getScore()*30);
            if(cameraLimit > 10500){ //Max length of background
                cameraLimit = 10500;
            }
            if(camera.position.x < cameraLimit ){
                camera.translate(10f, 0f);
            }
            Vector2 javelinPos = javelinController.updateJavelinPosition(javelin, normalThrow, posX, cameraLimit, deltaTime);
            javelin.getJavelinSprite().setPosition(javelinPos.x, javelinPos.y);
            javelin.getJavelinSprite().draw(sb);
        }

        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, loop), posX, 20);
        javelin.landedJavelin(camera);
        font.draw(sb, "Player: "+ player.getUsername() + " Country: "+ player.getCountry() + " Score: "+player.getScore()+" Round: "+round, camera.position.x-500, Gdx.graphics.getHeight()/2+150);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    //Check if previous score was better than second throw
    public void checkScore(){
        if(prevScore > player.getScore()){
            player.setScore(prevScore);
        }
    }

    //resets game between each round
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
        camera.position.set((float)((Gdx.graphics.getWidth()/2)), (float) ((Gdx.graphics.getHeight()/2)), 0 );
    }

    public void addButtons(){
        throwButtonImage = Assets.getTexture(Assets.throwButton);
        nextThrowImage = Assets.getTexture(Assets.newxtThrowButton);
        nextPlayerImage = Assets.getTexture(Assets.nextPlayer);
        finishGameImage = Assets.getTexture(Assets.goToScoreButton);
        pauseButtonImage = Assets.getTexture(Assets.pauseButton);
        resumeButtonImage = Assets.getTexture(Assets.resumeButton);
        quitButtonImage = Assets.getTexture(Assets.QuitButton);

        if (gsm.getGameRules().getPlayers().size() > 1){
            currentNextImage = nextPlayerImage;
        }
        else{
            currentNextImage = nextThrowImage;
        }

        final Button throwButton = new Button(new TextureRegionDrawable(new TextureRegion(throwButtonImage)));
        final Button nextThrowButton = new Button(new TextureRegionDrawable(new TextureRegion(currentNextImage)));
        final Button finishGameButton = new Button(new TextureRegionDrawable(new TextureRegion(finishGameImage)));
        Button pauseButton = new Button(new TextureRegionDrawable(new TextureRegion(pauseButtonImage)));
        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitButtonImage)));
        continueButton = new Button(new TextureRegionDrawable(new TextureRegion(resumeButtonImage)));

        throwButton.setHeight(Gdx.graphics.getHeight()/5);
        throwButton.setWidth((float) (throwButton.getHeight()*2.7));
        throwButton.setPosition(Gdx.graphics.getWidth() -throwButton.getWidth()-30, Gdx.graphics.getHeight()/7);

        nextThrowButton.setPosition(Gdx.graphics.getWidth() - nextThrowButton.getWidth()-30, Gdx.graphics.getHeight()/7);
        finishGameButton.setPosition(Gdx.graphics.getWidth() - finishGameButton.getWidth()-30, Gdx.graphics.getHeight()/7 );

        pauseButton.setPosition(Gdx.graphics.getWidth()-110, Gdx.graphics.getHeight()-110);
        pauseButton.setHeight(100);
        pauseButton.setWidth(100);

        quitButton.setPosition(20,20);

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

        quitButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.getGameRules().clearPlayers();
                gsm.set(new MenuState(gsm));
            }
        });


        continueButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                pause.remove();
            }
        });
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