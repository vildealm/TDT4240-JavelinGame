package com.mygdx.game.model.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuState extends State {
    private BitmapFont font;
    TextButton startButton;
    TextButton.TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
    Stage stage;


    public MenuState(GameStateManager gsm){
        super(gsm);
        stage = new Stage();
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("../android/assetsHeliSprites.png");
        skin.addRegions(buttonAtlas);
        textButtonStyle.fontColor = new Color(0f, 1f, 1f, 1f);
        textButtonStyle.font = font;
        //textButtonStyle.up = skin.getDrawable("up-button");
        //textButtonStyle.down = skin.getDrawable("down-button");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
        stage.addActor(startButton);

        startButton = new TextButton("START GAME", textButtonStyle);

        startButton.addListener(new ClickListener(){

            public void clicked(InputEvent event, Actor actor)
            {
                System.out.println("You clicked me!");
            }
        });
    }

    public void clickButton() {

    }

    @Override
    public void handleInput() {
        if(startButton.isPressed()){
            gsm.set(new GameState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        startButton.draw(sb,1 );
        sb.end();

    }

    public void dispose(){

    }
}
