package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.JavelinGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JavelinGame.WIDTH;
		config.height = JavelinGame.HEIGHT;
		config.title = JavelinGame.TITLE;


		new LwjglApplication(new JavelinGame(), config);
	}
}
