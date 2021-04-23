package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.EndState;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.HighscoreState;
import com.mygdx.game.model.states.MenuState;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class EndScreen implements Screen2 {
    private Texture HSbuttonTexture;
    private ArrayList<Player> players;
    private GameStateManager gsm;

    private BitmapFont getHighscoreFont;
    private TextButton.TextButtonStyle getHighscoreFontStyle;

    private Stage stage;
    private Viewport viewport;
    private Texture highscoreImage;
    private BitmapFont font;
    private Texture quitButtonImage;

    public EndScreen(final GameStateManager gsm) {
        super();
        this.gsm = gsm;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        stage = new Stage(new ScreenViewport());

        highscoreImage = Assets.getTexture(Assets.HighscoreButton);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        quitButtonImage = Assets.getTexture(Assets.QuitButton);

        players = gsm.getGameRules().getPlayers();
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p1.getScore().compareTo(p2.getScore());
            }
        });
        Collections.reverse(players);

        Button highscoreButton = new Button(new TextureRegionDrawable(new TextureRegion(highscoreImage)));
        highscoreButton.setPosition((float)(Gdx.graphics.getWidth()*0.8), (float) (Gdx.graphics.getHeight()*0.85));
        highscoreButton.setHeight((float) (Gdx.graphics.getHeight()*0.08));
        highscoreButton.setWidth((float) (Gdx.graphics.getWidth()*0.22));

        Button quitButton = new Button(new TextureRegionDrawable(new TextureRegion(quitButtonImage)));
        stage.addActor(quitButton);
        quitButton.setPosition((float) (Gdx.graphics.getWidth()*0.8), (float) (Gdx.graphics.getHeight()*0.75));
        quitButton.setHeight((float) (Gdx.graphics.getHeight()*0.1));
        quitButton.setWidth((float) (Gdx.graphics.getWidth()*0.22));
        Gdx.input.setInputProcessor(stage);

        stage.addActor(highscoreButton);
        stage.addActor(quitButton);
        Gdx.input.setInputProcessor(stage);

        highscoreButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new HighscoreState(gsm));
            }
        });

        quitButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.getGameRules().clearPlayers();
                gsm.set(new MenuState(gsm));
            }
        });
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta, SpriteBatch sb) {

        sb.begin();
        Matrix4 mat = new Matrix4();
        mat.setToOrtho2D(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setProjectionMatrix(mat);
        font.draw(sb,  "SCORES: ", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2 +200 );
        int counter = 1;
        for (int i=0; i < players.size(); i++){
            font.draw(sb, counter + ". " + players.get(i).getUsername() + ": " + players.get(i).getScore(), Gdx.graphics.getWidth()/2 -150, 500 - (i*100));
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
    public void dispose() {
        stage.dispose();
        getHighscoreFont.dispose();

    }
}
