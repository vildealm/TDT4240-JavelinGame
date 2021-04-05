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
    private Texture gameName;
    private Texture background;
    //private Texture playBtn;


    public MenuScreen(JavelinGame game, Engine engine){
        super();
        this.game = game;
        //gameName = Assets.getTexture(Assets.logo);
        background = Assets.getTexture(Assets.menuBackground);
        //playBtn = Assets.getTexture(Assets.gameScreenButton);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //Button startButton = createButton(playBtn,2f,"PLAY");
        //stage.addActor(startButton);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 1, 1);
        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws background photo
        //game.getBatch().draw(playBtn, Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2 );
        game.getBatch().end();
        stage.draw();
    }

    /*private Button createButton(Texture texture, float xPos, final String txtScreen){
        Button startButton = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        startButton.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        startButton.setPosition(Gdx.graphics.getWidth() / xPos - startButton.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - startButton.getHeight() / 2f);
        //startButton = new TextButton("START GAME", textButtonStyle);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float xpos, float ypos)
            {
                System.out.println("You clicked me!");
                game.gsm.updateScreen(txtScreen);
            }
        });
        return startButton;
    }
     */

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
