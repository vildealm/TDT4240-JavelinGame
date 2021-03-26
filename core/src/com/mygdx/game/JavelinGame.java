package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class JavelinGame extends ApplicationAdapter {
	SpriteBatch batch;
	FirebaseInterface _FBIC;

	public JavelinGame(FirebaseInterface FBIC){_FBIC = FBIC; }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		_FBIC.SomeFunction();
		_FBIC.FirstFirebaseTest();
		_FBIC.SetOnValueChangedListener();
		_FBIC.SetValueInDb("message", "this is new text");
		_FBIC.SetValueInDb("message2", "this is new text");

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
