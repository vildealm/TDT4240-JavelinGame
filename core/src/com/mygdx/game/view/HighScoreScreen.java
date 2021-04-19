package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.controller.FirebaseInterface;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.components.Score;
import com.mygdx.game.model.states.GameStateManager;

import java.util.ArrayList;

public class HighScoreScreen implements Screen2 {
    private SpriteBatch batch;
    private FirebaseInterface _FBIC;
    private ArrayList<Score> highscores;
    private int pos;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private GameStateManager gsm;

    public HighScoreScreen(FirebaseInterface FBIC, GameStateManager gsm){
        _FBIC = FBIC;
        this.gsm = gsm;
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        _FBIC.initUser();
        //_FBIC.setValueInDb("AndyTorky", 70.0, "NOR");
        highscores = _FBIC.getDataFromDb();
        //pos = _FBIC.getUserPos();
    }

/*
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        _FBIC.initUser();
        //_FBIC.setValueInDb("AndyTorky", 70.0, "NOR");
        highscores = _FBIC.getDataFromDb();
        //pos = _FBIC.getUserPos();
    }*/



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
    }
}
