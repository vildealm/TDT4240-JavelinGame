package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.LoadingState;
import com.mygdx.game.model.states.MenuState;
import com.mygdx.game.model.states.SetupState;
import com.mygdx.game.view.Screen2;

public class JavelinGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gsm;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Javelin Game";
	public ScreenFactory screenFactory;
	protected Screen2 screen;
	private Stage stage;

	public static final JavelinGame INSTANCE = new JavelinGame();

	public JavelinGame(){

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(this);
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new LoadingState(gsm));
		screenFactory = new ScreenFactory(gsm);
		gsm.push(new MenuState(gsm));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.log("JavelinGamegsm", String.valueOf(gsm));


	}


	public static JavelinGame getInstance() {
		return INSTANCE;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setScreen (Screen2 screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	@Override
	public void render () {
		/*if (screen!= null) {
			screen.render(Gdx.graphics.getDeltaTime(),batch);
		}*/
		//gsm.renderBatch(batch);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//gsm.renderScreen(batch);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		if(screen!=null){
			gsm.renderBatch(batch);
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
