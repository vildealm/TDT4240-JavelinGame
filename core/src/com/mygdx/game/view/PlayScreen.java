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
import com.mygdx.game.model.components.Javelin;
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
    private TextButton tapArea;
    private TextButton.TextButtonStyle tapAreaStyle;
    private BitmapFont tapAreafont;





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

        //tapArea
        tapAreafont = new BitmapFont();
        tapAreafont.setColor(Color.BLACK);
        tapAreafont.getData().setScale(3);
        tapAreaStyle = new TextButton.TextButtonStyle();
        tapAreaStyle.font = tapAreafont;

        TextButton tapArea = new TextButton("", tapAreaStyle);
        tapArea.setPosition(0,0);
        tapArea.setWidth((Gdx.graphics.getWidth()*2)/3);
        tapArea.setHeight(Gdx.graphics.getHeight());
        tapArea.getLabel().setFontScale(5, 5);
        stage.addActor(tapArea);


        //Player
        man = new TextureAtlas(Gdx.files.internal("Runsprites/run.atlas"));
        throwMan = new TextureAtlas(Gdx.files.internal("Throwsprites/throw.atlas"));
        runningMan = new Animation(5f/ 20f, man.getRegions());
        throwingMan = new Animation(5f/20f, throwMan.getRegions());
        currentAnim = runningMan;



        tapArea.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                if(speedX<400){
                    speedX = speedX + 20;
                }
            }
        });

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        posX += Gdx.graphics.getDeltaTime() * speedX;

        sb.begin();
        sb.draw((TextureRegion) currentAnim.getKeyFrame(elapsedTime, true),posX, 20);
        font.draw(sb, "Speed: "+ speedX + " Dist:"+random+" Score: "+attempt, 500, 600);
        sb.end();

        //Makes line, showing where to throw
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.line(600, 0, 600, 100);
        shapeRenderer.end();

         */
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
    }
}
