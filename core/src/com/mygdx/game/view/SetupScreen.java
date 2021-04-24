package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.components.PlayerInputBox;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MultiplayerSelectionState;

import java.util.ArrayList;

public class SetupScreen implements Screen2{

    //Stage
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private Player player;
    private int xPosition = Gdx.graphics.getWidth()/20;
    private int posChange = Gdx.graphics.getWidth()/4;
    //Components
    private ArrayList<Player> players;
    private ArrayList<PlayerInputBox> elements;
    private PlayerInputBox inputPlayer;
    private Sprite background;
    private int numberOfPlayers;
    //Button
    private TextButton.TextButtonStyle playButtonStyle;
    private Texture buttonImage;
    private Texture playerBox;
    private TextureRegion region;
    private Image box;
    private Sprite playerBoxSprite;
    private Texture backImage;



    public SetupScreen(final GameStateManager gsm) {
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        //GUI Components
        elements = new ArrayList<>();
        players = new ArrayList<>();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));
        playerBox = Assets.getTexture(Assets.playerBackground);
        playerBoxSprite = new Sprite(playerBox);
        region = new TextureRegion(playerBox);
        //Sets the player number
        this.numberOfPlayers = gsm.getGameRules().getNumberOfPlayers();
        if (numberOfPlayers == 4) {
            this.posChange =  ((Gdx.graphics.getWidth()*6)/26);
        }
        if (numberOfPlayers == 3) {
            xPosition = (Gdx.graphics.getWidth()*3)/18;
        }
        if (numberOfPlayers == 2) {
            xPosition += (Gdx.graphics.getWidth()*2)/9;
        }
        if (numberOfPlayers == 1) {
            xPosition = (Gdx.graphics.getWidth()/2)-(playerBox.getWidth()/2);
        }

        //Creating the PLAY and BACK button.
        font = new BitmapFont();
        buttonImage = Assets.getTexture(Assets.playButton);
        backImage = Assets.getTexture(Assets.backButton);
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(buttonImage)));
        Button backButton = new Button(new TextureRegionDrawable(new TextureRegion(backImage)));
        backButton.setHeight((float) (Gdx.graphics.getHeight()*0.1));
        backButton.setWidth((float) (Gdx.graphics.getWidth()*0.25));
        backButton.setPosition((float) (Gdx.graphics.getWidth()*0.02), (float) (Gdx.graphics.getHeight()*0.88));
        playButton.setPosition((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2), Gdx.graphics.getHeight()/11);
        stage.addActor(playButton);
        stage.addActor(backButton);

        //Adds GUI elements to the screen
        for (int i = 0; i < this.numberOfPlayers; i++) {
            inputPlayer = new PlayerInputBox(xPosition,i+1);
            player = new Player();
            players.add(player);
            elements.add(inputPlayer);
            box = new Image(region);
            box.setPosition(xPosition - 10, ((Gdx.graphics.getHeight()*3)/5));
            box.setSize((Gdx.graphics.getWidth()*2)/9,(Gdx.graphics.getHeight()*2)/7);
            stage.addActor(box);
            xPosition += posChange;
        }
        for (int i = 0; i < elements.size(); i++) {
            stage.addActor(elements.get(i).getTextfield());
            stage.addActor(elements.get(i).getSelectbox());
            stage.addActor(elements.get(i).getIdTxt());
            stage.addActor(elements.get(i));
        }
        //Button ClickListener
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (inputPlayer.checkInputFields()) {
                    for (int i = 0; i < players.size(); i++) {
                        players.get(i).setUsername(elements.get(i).getUsername());
                        players.get(i).setCountry(elements.get(i).getCountry());
                    }
                    gsm.getGameRules().setPlayers(players);
                    gsm.set(new GameState(gsm));
                } else {
                    stage.addActor(inputPlayer.getErrorMsg());
                }
            }
        });

        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                gsm.set(new MultiplayerSelectionState(gsm));
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