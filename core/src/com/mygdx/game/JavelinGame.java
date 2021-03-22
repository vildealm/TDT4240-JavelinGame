package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import pl.mk5.gdx.fireapp.GdxFIRApp;
import pl.mk5.gdx.fireapp.GdxFIRAuth;
import pl.mk5.gdx.fireapp.GdxFIRCrash;
import pl.mk5.gdx.fireapp.GdxFIRDatabase;
import pl.mk5.gdx.fireapp.auth.GdxFirebaseUser;
import pl.mk5.gdx.fireapp.database.FilterType;
import pl.mk5.gdx.fireapp.functional.BiConsumer;
import pl.mk5.gdx.fireapp.functional.Consumer;

public class JavelinGame extends ApplicationAdapter {
	SpriteBatch batch;
	String userDisplayName = "hello";
	BitmapFont font;

	public class Score{
		public String username;
		public int score;
		public Score(){}
		public Score(String username, int score){
			this.username = username;
			this.score = score;
		}
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		GdxFIRApp.inst().configure();

		GdxFIRAuth.inst().signInAnonymously().then(new Consumer<GdxFirebaseUser>() {
			@Override
			public void accept(GdxFirebaseUser user) {
				userDisplayName = user.getUserInfo().getDisplayName();
				GdxFIRDatabase.inst()
						.inReference("/scores/GWNR7SmRukICshOPZkUE")
						.push()
						.setValue(new Score("Bob", 54));
				/*GdxFIRDatabase.inst().inReference("GWNR7SmRukICshOPZkUE")
						.readValue(List.class)
						.then(new Consumer<List<Score>>() {
							@Override
							public void accept(List<Score> score) {
								Gdx.app.log("user", "joel");
							}

						});*/
			}
		}).fail(new BiConsumer<String, Throwable>() {
			@Override
			public void accept(String s, Throwable throwable) {
			}
		});

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(2,2);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, userDisplayName, 100, 100);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
