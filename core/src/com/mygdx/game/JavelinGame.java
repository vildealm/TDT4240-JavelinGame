package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenFactory;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.model.states.MenuState;

public class JavelinGame extends Game {
	private SpriteBatch batch;
	private static JavelinGame INSTANCE;
	private GameStateManager gsm;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	private Engine engine;
	private Assets assets;
	public ScreenFactory screenFactory;

	public static final String TITLE = "Javelin Game";


	public SpriteBatch getBatch() { return batch; }
	public static JavelinGame getInstance() {
		return INSTANCE;
	}

	@Override
	public void create () {
			assets = new Assets();
			engine = new Engine();
			batch = new SpriteBatch();
		gsm = new GameStateManager(this);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//gsm.push(new MenuState(gsm));
		screenFactory = new ScreenFactory();
	}

	@Override
	public void render () {
		/*Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);*/
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
