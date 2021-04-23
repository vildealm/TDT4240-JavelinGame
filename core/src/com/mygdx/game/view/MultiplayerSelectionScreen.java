package com.mygdx.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;
import com.mygdx.game.model.states.SetupState;

import java.util.ArrayList;


public class MultiplayerSelectionScreen implements Screen2 {

    private BitmapFont font;
    private Stage stage;
    private GameStateManager gsm;
    private TextureAtlas buttonAtlas;
    private Button onePlayerButton;
    private Button twoPlayerButton;
    private Button threePlayerButton;
    private Button fourPlayerButton;
    private Button backButton;
    private Texture onePlayerButtonImage;
    private Texture twoPlayerButtonImage;
    private Texture threePlayerButtonImage;
    private Texture fourPlayerButtonImage;
    private Texture backButtonTexture;

    private int xPos;

    private ArrayList<Button> playerButtons;

    private Sprite background;


    public MultiplayerSelectionScreen(final GameStateManager gsm){
        super();
        this.gsm = gsm;
        stage = new Stage(new ScreenViewport());

        onePlayerButtonImage = Assets.getTexture(Assets.onePlayerButton);
        twoPlayerButtonImage = Assets.getTexture(Assets.twoPlayerButton);
        threePlayerButtonImage = Assets.getTexture(Assets.threePlayerButton);
        fourPlayerButtonImage = Assets.getTexture(Assets.fourPlayerButton);
        backButtonTexture = Assets.getTexture(Assets.backButton);


        font = new BitmapFont();
        playerButtons = new ArrayList<>();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));


        //Multiplayer buttons
        onePlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(onePlayerButtonImage)));
        twoPlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(twoPlayerButtonImage)));
        threePlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(threePlayerButtonImage)));
        fourPlayerButton = new Button(new TextureRegionDrawable(new TextureRegion(fourPlayerButtonImage)));
        backButton = new Button(new TextureRegionDrawable(new TextureRegion(backButtonTexture)));
        playerButtons.add(onePlayerButton);
        playerButtons.add(twoPlayerButton);
        playerButtons.add(threePlayerButton);
        playerButtons.add(fourPlayerButton);

        stage.addActor(backButton);
        backButton.setHeight((float) (Gdx.graphics.getHeight()*0.1));
        backButton.setWidth((float) (Gdx.graphics.getWidth()*0.25));
        backButton.setPosition((float) (Gdx.graphics.getWidth()*0.03), (float) (Gdx.graphics.getHeight()*0.85));

        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new MenuState(gsm));
            }
        });

        xPos = Gdx.graphics.getWidth()/10;

        for(final Button i : playerButtons){
            i.setPosition(xPos,Gdx.graphics.getHeight()/2-(i.getHeight()/2));
            xPos += Gdx.graphics.getWidth()/4.8;
            i.setTransform(true);
            i.setScale(0.8f);
            stage.addActor(i);
            i.addListener(new ChangeListener(){
                @Override
                public void changed(ChangeEvent event, Actor actor){
                    gsm.getGameRules().setNumberOfPlayers(playerButtons.indexOf(i)+1);
                    gsm.set(new SetupState(gsm));
                }
            });
        }

        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();

    }

    public void show() { }

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "MENU", 70, 100);
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

