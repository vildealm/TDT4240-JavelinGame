package com.mygdx.game.view;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Player;
import com.mygdx.game.model.states.GameStateManager;

import java.util.ArrayList;

public class SetupScreen implements Screen2{

    //Stage ;
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private TextField usernameInput;
    private int xPosition, yPosition;
    //private SelectBox sb;

    //Background
    Sprite playerSprite;
    Texture txtSprite;
    ArrayList<Player> players;

    public SetupScreen(GameStateManager gsm, int numberOfPlayers){
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        players = new ArrayList<>();
        playerSprite = new Sprite(Assets.getTexture(Assets.player));
        txtSprite = Assets.getTexture(Assets.txtfieldBackground);
        font = new BitmapFont();
        Gdx.input.setInputProcessor(stage);

        xPosition = 300;
        for(int i=0; i<numberOfPlayers; i++){
            usernameInput = makeInputField(xPosition+150);
            Label username = makeLabel("Username:",Color.BLACK,xPosition, 400);
            Label country = makeLabel("Choose country:",Color.BLACK, xPosition, 350);
            SelectBox sb = makeDropdown(xPosition+150);
            stage.addActor(sb);
            stage.addActor(username);
            stage.addActor(country);
            stage.addActor(usernameInput);
            xPosition += 400;
        }
    }

    public void show() {}

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        sb.draw(playerSprite, 0,0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 );
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private TextField makeInputField(int xPos){
        SpriteDrawable s = new SpriteDrawable(new Sprite(txtSprite));
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(new BitmapFont(),Color.BLACK,null,null,s);

        TextField tf = new TextField("", style);
        tf.setSize(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/28f);
        tf.setPosition(xPos,400);
        return tf;
    }

    private Label makeLabel(String text, Color color, int xPos, int yPos){
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), color);

        Label l = new Label(text, style);
        l.setPosition(xPos,yPos);
        l.setFontScale(1.3f);
        return l;
    }


    private SelectBox makeDropdown(int xPos){
        SpriteDrawable s1 = new SpriteDrawable(new Sprite(txtSprite));
        ScrollPane.ScrollPaneStyle sp = new ScrollPane.ScrollPaneStyle();
        List.ListStyle ls = new List.ListStyle(font, Color.WHITE, Color.BLACK, s1);
        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle(font, Color.BLACK, s1, sp, ls);

        SelectBox<String> selectBox = new SelectBox<>(boxStyle);
        String[] countries = new String[]{"---","NOR","SVE","DAN","FIN"};
        selectBox.setItems(countries);
        selectBox.setPosition(xPos, 350);
        selectBox.setSize(110, 37);
        return selectBox;
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
