package com.mygdx.game.view;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.components.inputPlayer;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;

import java.util.ArrayList;

public class SetupScreen implements Screen2{

    //Stage
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private Player player;
    private int xPosition = 50;
    //Components
    private ArrayList<Player> players;
    private ArrayList<inputPlayer> elements;
    private inputPlayer inputPlayer;
    private Sprite background;
    //Button
    private TextButton.TextButtonStyle playButtonStyle;


    public SetupScreen(final GameStateManager gsm, int numberOfPlayers){
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

       //Components
        elements = new ArrayList<>();
        players = new ArrayList<>();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));

        //Button
        font = new BitmapFont();
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = font;
        TextButton playButton = new TextButton("PLAY", playButtonStyle);
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        playButton.getLabel().setFontScale(5, 5);
        stage.addActor(playButton);
        for(int i=0; i<numberOfPlayers; i++) {
            inputPlayer = new inputPlayer(xPosition);
            player = new Player();
            players.add(player);
            elements.add(inputPlayer);
            xPosition += 300;
        }
        for(int i=0; i<elements.size(); i++){
            stage.addActor(elements.get(i).getTextfield());
            stage.addActor(elements.get(i).getSelectbox());
            stage.addActor(elements.get(i));
        }
        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                if(inputPlayer.checkInputFields()) {
                    for(int i = 0; i<players.size(); i++){
                        players.get(i).setUsername(elements.get(i).getUsername());
                        players.get(i).setCountry(elements.get(i).getCountry());
                    }
                    gsm.set(new MenuState(gsm));
                }else{
                    stage.addActor(inputPlayer.getErrorMsg());
                }
            }
        });
    }
    public void show() {}

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
