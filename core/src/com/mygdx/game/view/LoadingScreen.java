package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;

public class LoadingScreen implements Screen2 {

    private GameStateManager gsm;
    private float progress;
    private Stage stage;
    private BitmapFont font;
    private Assets assets;

    public LoadingScreen(GameStateManager gsm, Assets assets){
        this.gsm = gsm;
        this.assets = assets;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        this.progress = 0f;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "LOADING...", 70, 100);
        sb.end();
        progress = MathUtils.lerp(progress, Assets.getProgress(), .1f);
        if (Assets.update() && progress >= Assets.getProgress() - 0.001f) {
            dispose();
            gsm.set(new MenuState(gsm));
        }
        Gdx.gl.glClearColor(187.0f/255.0f, 231.0f/255.0f, 255.0f/255.0f, 1.0f);
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

    }
}
