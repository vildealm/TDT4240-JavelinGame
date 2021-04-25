package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;
import com.mygdx.game.model.states.MultiplayerSelectionState;


public class LearnScreen implements Screen2 {
    private GameStateManager gsm;
    private Stage stage;
    private Sprite learnSprite;
    private BitmapFont font;
    private BitmapFont font2;
    private Texture backButtonImage;
    private Button backButton;



    public LearnScreen(final GameStateManager gsm){
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        learnSprite = new Sprite(Assets.getTexture(Assets.learnImage));
        font = new BitmapFont();
        font2 = new BitmapFont();

        //button
        backButtonImage = Assets.getTexture(Assets.backButton);
        backButton = new Button(new TextureRegionDrawable(new TextureRegion(backButtonImage)));
        backButton.setHeight((float) (Gdx.graphics.getHeight()*0.1));
        backButton.setWidth((float) (Gdx.graphics.getWidth()*0.25));
        backButton.setPosition((float) (Gdx.graphics.getWidth()*0.03), (float) (Gdx.graphics.getHeight()*0.85));
        stage.addActor(backButton);

        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new MenuState(gsm));
            }
        });

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {sb.begin();
        font.setColor(Color.BLACK);
        font2.setColor(Color.BLACK);
        sb.draw(learnSprite,(Gdx.graphics.getWidth() /2) - (learnSprite.getWidth()/2) -150,(Gdx.graphics.getHeight()/2) - (learnSprite.getHeight()/2),     900 ,450);
        font.draw(sb, "Tap in the red area to gain speed", (float) ((Gdx.graphics.getWidth()*0.5)-Gdx.graphics.getWidth()/2.3), 200);
        font.draw(sb, "Tap on THROW to throw the javelin" , (float) (Gdx.graphics.getWidth()*0.52), 200);
        font2.draw(sb, "Each player gets to throw 2 times per game." , (Gdx.graphics.getWidth() /2) - 250, 150);
        font2.draw(sb, "If you do not throw before the line, you get zero points." , (Gdx.graphics.getWidth() /2) - 300, 110);
        font2.draw(sb, "High speed ->  higher score" , (Gdx.graphics.getWidth() /2 ) -150, 70);
        font2.draw(sb, "Close to the line ->  higher score" , (Gdx.graphics.getWidth() /2 ) -180, 30);

        font.getData().setScale(2.5f);
        font2.getData().setScale(2f);
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
    public void dispose() {
        stage.dispose();
    }
}
