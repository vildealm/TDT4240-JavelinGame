package com.mygdx.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private TextButton throwArea;
    private TextButton.TextButtonStyle throwAreaStyle;
    private BitmapFont throwAreafont;

    //PLayerController
    private PlayerController playerController;

    //Player
    private Player player;



    Animation javelin1;
    private Javelin javelin2;
    private Stage stage;

    private Vector2 javelinPosition = new Vector2();
    private Vector2 javelinVelocity = new Vector2();
    private float javelinStateTime = 0;
    private Vector2 javelinGravity = new Vector2();

    public PlayScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
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

        //throwArea
        throwAreafont = new BitmapFont();
        throwAreafont.setColor(Color.BLACK);
        throwAreafont.getData().setScale(3);
        throwAreaStyle = new TextButton.TextButtonStyle();
        throwAreaStyle.font = runAreafont;

        TextButton throwArea = new TextButton("", runAreaStyle);
        throwArea.setPosition((Gdx.graphics.getWidth()*2)/3,0);
        throwArea.setWidth((Gdx.graphics.getWidth())/3);
        throwArea.setHeight(Gdx.graphics.getHeight());
        throwArea.getLabel().setFontScale(5, 5);
        stage.addActor(throwArea);



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

        throwArea.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                random=(600-(posX+40));
                player.setScore(playerController.getSpeed(), (600-(posX+40)));
            }
        });

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        posX += Gdx.graphics.getDeltaTime() * playerController.getSpeed();
        playerController.reduceSpeed();

        sb.begin();
        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, true),posX, 20);
        font.draw(sb, "Speed: "+ playerController.getSpeed() + " Dist:"+random+" Score: "+player.getScore(), 300, 600);
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
