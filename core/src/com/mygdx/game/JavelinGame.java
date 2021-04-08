package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;

public class JavelinGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gsm;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Javelin Game";
	public ScreenFactory screenFactory;
	protected Screen screen;

	public static final JavelinGame INSTANCE = new JavelinGame();

	public JavelinGame(){

			batch = new SpriteBatch();
			gsm = new GameStateManager(this);
			//Gdx.gl.glClearColor(1, 0, 0, 1);
			gsm.push(new MenuState(gsm));
			screenFactory = new ScreenFactory();
	}



	public static JavelinGame getInstance() {
		return INSTANCE;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setScreen (Screen screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.renderScreen(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
