package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.states.GameStateManager;

public class SetupScreen implements Screen2{

    //Stage ;
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private TextField username;

    //Background
    Sprite playerSprite;
    Texture txtSprite;

    public SetupScreen(GameStateManager gsm){
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        playerSprite = new Sprite(Assets.getTexture(Assets.player));
        txtSprite = Assets.getTexture(Assets.txtfieldBackground);
        font = new BitmapFont();
        Gdx.input.setInputProcessor(stage);
        username = makeInputField(0.96f);
        stage.addActor(username);
        Label username = makeLabel("Username:",Color.BLACK,14f,4.95f);
        Label country = makeLabel("Choose country:",Color.BLACK, 20.95f,6.5f);
        stage.addActor(username);
        stage.addActor(country);
    }

    public void show() {}

    public void render(float delta, SpriteBatch sb) {
        sb.begin();
        sb.draw(playerSprite, 0,0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 );
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private TextField makeInputField(float xPos){
        SpriteDrawable s = new SpriteDrawable(new Sprite(txtSprite));
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(new BitmapFont(),Color.BLACK,null,null,s);

        TextField tf = new TextField("", style);
        tf.setSize(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/28f);
        tf.setPosition((Gdx.graphics.getWidth() / 8f) * xPos,(Gdx.graphics.getHeight() / 4.5f) - (tf.getHeight()/100f*80f));
        return tf;
    }

    private Label makeLabel(String text, Color color, float xPos, float yPos){
        Label l = new Label(text, new Label.LabelStyle(new BitmapFont(), color));
        l.setPosition(Gdx.graphics.getWidth() /xPos,Gdx.graphics.getHeight() / yPos);
        l.setFontScale(1.3f);
        return l;
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
