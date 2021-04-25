package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.backend.FirebaseInterface;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.LoadingState;
import com.mygdx.game.view.Screen2;

public class JavelinGame extends ApplicationAdapter {
	private FirebaseInterface _FBIC;
	public JavelinGame(FirebaseInterface FBIC){_FBIC = FBIC; }
	private GameStateManager gsm;
	private Assets assets;
	public ScreenFactory screenFactory;
	private SpriteBatch batch;
	protected Screen2 screen;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Javelin Game";


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(this);
		assets = new Assets();
		assets.load();
		screenFactory = new ScreenFactory(gsm,assets);
		gsm.push(new LoadingState(gsm));
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(165.0f/255.0f, 214.0f/255.0f, 244.0f/255.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		if(screen!=null){
			gsm.renderBatch(batch);
		}
	}

	public FirebaseInterface getFirebaseInterface(){
		return _FBIC;
	}

	public void setScreen (Screen2 screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	public GameStateManager getGsm(){
		return this.gsm;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
