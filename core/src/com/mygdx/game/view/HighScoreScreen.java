package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.controller.FirebaseInterface;
import com.mygdx.game.model.components.Player;

import java.util.ArrayList;

public class HighScoreScreen extends ApplicationAdapter {
    private SpriteBatch batch;
    private FirebaseInterface _FBIC;
    private ArrayList<Player> highscores;
    private int pos;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public HighScoreScreen(FirebaseInterface FBIC){_FBIC = FBIC; }

    @Override
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
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        for(int j=0; j< highscores.size(); j++){
            shapeRenderer.rect(200, 5+(j*75), 800, 70);
        }
        shapeRenderer.end();
        int counter = 1;
        batch.begin();
        for(int i=highscores.size()-1; i>= 0; i--){
            font.draw(batch, (counter)+". "+ highscores.get(i).getUsername(), 250, 50+(i*75));
            font.draw(batch, highscores.get(i).getScore().toString(), 650, 50+(i*75));
            font.draw(batch, highscores.get(i).getCountry(), 850, 50+(i*75));
            counter++;
        }
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }
}
