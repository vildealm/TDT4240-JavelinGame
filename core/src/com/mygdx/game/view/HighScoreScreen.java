package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.backend.FirebaseInterface;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Score;
import com.mygdx.game.model.states.EndState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;

import java.util.ArrayList;

public class HighScoreScreen implements Screen2 {
    private FirebaseInterface _FBIC;
    private GameStateManager gsm;
    private Stage stage;

    private ArrayList<Score> highscores;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Texture backButtonImage;
    private Texture quitButtonImage;
    private Button backButton;
    private Button quitButton;


    public HighScoreScreen( final GameStateManager gsm){
        this.gsm = gsm;
        _FBIC = gsm.game.getFirebaseInterface();
        stage = new Stage(new ScreenViewport());
        shapeRenderer = new ShapeRenderer();
        _FBIC.initUser();

        highscores = _FBIC.getDataFromDb();

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        
        //buttons
        backButtonImage = Assets.getTexture(Assets.backButton);
        quitButtonImage = Assets.getTexture(Assets.QuitButton);

        backButton = new Button(new TextureRegionDrawable(new TextureRegion(backButtonImage)));
        backButton.setPosition((float) (Gdx.graphics.getWidth()*0.03), (float) (Gdx.graphics.getHeight()*0.86));
        backButton.setHeight((float) (Gdx.graphics.getHeight()*0.1));
        backButton.setWidth((float) (Gdx.graphics.getWidth()*0.2));
        stage.addActor(backButton);

        quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitButtonImage)));
        stage.addActor(quitButton);
        quitButton.setPosition((float) (Gdx.graphics.getWidth()*0.87), (float) (Gdx.graphics.getHeight()*0.85));
        quitButton.setHeight((float) (Gdx.graphics.getHeight()*0.12));
        quitButton.setWidth((float) (Gdx.graphics.getWidth()*0.12));
        Gdx.input.setInputProcessor(stage);

        quitButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                highscores.clear();
                gsm.getGameRules().clearPlayers();
                gsm.set(new MenuState(gsm));
            }
        });

        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                highscores.clear();
                gsm.set(new EndState(gsm));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        Gdx.gl.glClearColor(165.0f/255.0f, 214.0f/255.0f, 244.0f/255.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(sb.getTransformMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        for(int j=0; j< highscores.size(); j++){
            shapeRenderer.rect((float) (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.9)), (float) (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight())+j*Gdx.graphics.getHeight()*0.1), (float) (Gdx.graphics.getWidth()*0.8), Gdx.graphics.getHeight()/11);
        }
        shapeRenderer.end();

        int counter = 1;
        sb.begin();
        for(int i=highscores.size()-1; i>= 0; i--){
            font.draw(sb, (counter)+". "+ highscores.get(i).getUsername(), (float) (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.85)), (float) (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94)+i*Gdx.graphics.getHeight()*0.1)); //5+(j*75)
            font.draw(sb, highscores.get(i).getScore().toString(), (float) (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.5)), (float) (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94)+i*Gdx.graphics.getHeight()*0.1));
            font.draw(sb, highscores.get(i).getCountry(), (float) (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.25)), (float) (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94)+i*Gdx.graphics.getHeight()*0.1));
            counter++;
        }
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
    public void dispose () {
        font.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }
}
