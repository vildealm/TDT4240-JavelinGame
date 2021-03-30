package com.mygdx.game.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.model.Assets;

public class MenuScreen implements Screen {

    private Stage stage;
    private JavelinGame game;

    private Texture background;
    private Texture playBtn;


    public MenuScreen(JavelinGame game, Engine engine){
        super();
        this.game = game;
        background = Assets.getTexture(Assets.mainBackground);
        playBtn = Assets.getTexture(Assets.gameScreenButton);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Button startButton = createButton(playBtn,2f,"PLAY");
        stage.addActor(startButton);

    }

    @Override
    public void render(float delta) {
        game.getBatch().begin(); // Draw elements to Sprite Batch
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        game.getBatch().end();
        stage.draw();
    }

    private Button createButton(Texture texture, float xPos, final String nextScreen){
        Button startButton = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        //startButton = new TextButton("START GAME", textButtonStyle);

        startButton.addListener(new ClickListener(){

            public void clicked(InputEvent event, Actor actor)
            {
                System.out.println("You clicked me!");
            }
        });
        return startButton;
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
