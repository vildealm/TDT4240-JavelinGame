package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.Assets;

public class LoadingScreen implements Screen2 {

    private JavelinGame game;
    private float progress;
    private Stage stage;
    public String type;

    public LoadingScreen(JavelinGame game){
        this.game = game;
        Assets.load();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {

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

    }
}
