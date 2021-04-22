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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.components.inputPlayer;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.GameStateManager;
import java.util.ArrayList;

public class SetupScreen implements Screen2{

    //Stage
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private Player player;
    private int xPosition = 50;
    private int posChange = 300;
    //Components
    private ArrayList<Player> players;
    private ArrayList<inputPlayer> elements;
    private inputPlayer inputPlayer, currentPlayer;
    private Sprite background;
    private int numberOfPlayers;
    //Button
    private TextButton.TextButtonStyle playButtonStyle;
    private Texture buttonImage;
    private Texture playerBox;
    private TextureRegion region;
    private Image box;
    private Sprite playerBoxSprite;
    private Label playerNr;


    public SetupScreen(final GameStateManager gsm){
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        this.numberOfPlayers = gsm.getGameRules().getNumberOfPlayers();
       //Components
        elements = new ArrayList<>();
        players = new ArrayList<>();
        background = new Sprite(Assets.getTexture(Assets.setupBackground));
        playerBox = Assets.getTexture(Assets.playerBackground);
        playerBoxSprite = new Sprite(playerBox);
        region = new TextureRegion(playerBox);
        if(numberOfPlayers==4){
            this.posChange = 275;
        }

        if (numberOfPlayers==3) {
            xPosition+=137.5;
        }
        if(numberOfPlayers==2){
            xPosition+=275;
        }
        if(numberOfPlayers==1){
            xPosition+=412.5;
        }

        //Button
        font = new BitmapFont();
        buttonImage = Assets.getTexture(Assets.playButton);
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(buttonImage)));
        playButton.setPosition((Gdx.graphics.getWidth()/2)-(playButton.getWidth()/2), 100);
        stage.addActor(playButton);


        for(int i=0; i<this.numberOfPlayers; i++) {
            inputPlayer = new inputPlayer(xPosition, i+1);
            player = new Player();
            players.add(player);
            elements.add(inputPlayer);
            box = new Image(region);
            box.setPosition(xPosition-10,370);
            stage.addActor(box);
            xPosition += posChange;
        }
        for(int i=0; i<elements.size(); i++){
            currentPlayer = elements.get(i);
            stage.addActor(currentPlayer.getTextfield());
            stage.addActor(currentPlayer.getSelectbox());
            stage.addActor(currentPlayer);
            stage.addActor(currentPlayer.getIdTxt());

        }
        playButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                if(inputPlayer.checkInputFields()) {
                    for(int i = 0; i<players.size(); i++){
                        players.get(i).setUsername(elements.get(i).getUsername());
                        players.get(i).setCountry(elements.get(i).getCountry());
                    }
                    gsm.getGameRules().setPlayers(players);
                    gsm.set(new GameState(gsm));
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
