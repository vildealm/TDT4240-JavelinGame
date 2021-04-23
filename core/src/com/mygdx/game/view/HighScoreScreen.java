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
    private ArrayList<Score> highscores;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private GameStateManager gsm;
    private Stage stage;
    Texture buttonImage;
    Texture quitButton;


    public HighScoreScreen(FirebaseInterface FBIC, final GameStateManager gsm){
        _FBIC = FBIC;
        if(highscores!=null){
            highscores.clear();
        }
        this.gsm = gsm;
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        _FBIC.initUser();
        highscores = _FBIC.getDataFromDb();

        stage = new Stage(new ScreenViewport());
        buttonImage = Assets.getTexture(Assets.backButton);
        quitButton = Assets.getTexture(Assets.QuitButton);

        Button settingButton = new Button(new TextureRegionDrawable(new TextureRegion(buttonImage)));
        settingButton.setPosition(80, 650);
        settingButton.setHeight(100);
        settingButton.setWidth(350);
        stage.addActor(settingButton);

        Button quitButton1 = new Button(new TextureRegionDrawable(new TextureRegion(quitButton)));
        stage.addActor(quitButton1);
        quitButton1.setPosition(900, 650);
        quitButton1.setHeight(100);
        quitButton1.setWidth(300);
        Gdx.input.setInputProcessor(stage);

        quitButton1.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                highscores.clear();
                gsm.getGameRules().clearPlayers();
                gsm.set(new MenuState(gsm));
            }
        });

        settingButton.addListener(new ChangeListener(){
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
            shapeRenderer.rect(200, 5+(j*75), 800, 70);
        }
        shapeRenderer.end();
        int counter = 1;
        sb.begin();
        for(int i=highscores.size()-1; i>= 0; i--){
            font.draw(sb, (counter)+". "+ highscores.get(i).getUsername(), 250, 50+(i*75));
            font.draw(sb, highscores.get(i).getScore().toString(), 650, 50+(i*75));
            font.draw(sb, highscores.get(i).getCountry(), 850, 50+(i*75));
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
