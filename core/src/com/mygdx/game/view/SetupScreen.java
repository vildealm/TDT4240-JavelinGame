package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.components.Javelin;
import com.mygdx.game.model.components.Score;
import com.mygdx.game.model.states.GameStateManager;



public class SetupScreen implements Screen2{

    //Stage ;
    private GameStateManager gsm;
    private Stage stage;
    private BitmapFont font;
    private Javelin javelin;



    //Background
    Sprite setupSprite;
    Sprite playerSprite;

    public SetupScreen(GameStateManager gsm){
        super();
        ScreenViewport viewport = new ScreenViewport();
        this.gsm = gsm;
        this.stage = new Stage(viewport);
        setupSprite = new Sprite(Assets.getTexture(Assets.setupBackground));
        playerSprite = new Sprite(Assets.getTexture(Assets.player));
        font = new BitmapFont();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(javelin);
        stage.addTouchFocus(new InputListener(), javelin, javelin, 1,1); }

    public void show() {}

    public void render(float delta, SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        font.draw(sb, "SETUP", 70, 100);
        sb.draw(setupSprite, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playerSprite, 0,0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 );
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
