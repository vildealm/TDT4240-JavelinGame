package com.mygdx.game.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.mygdx.game.JavelinGame;
import com.mygdx.game.view.MenuScreen;

public class ScreenFactory {
    public static JavelinGame game = JavelinGame.getInstance();
    public static Engine engine = new Engine();


    public static Screen getScreen(String screenType){
        switch (screenType){
            case "MENU":
                return new MenuScreen(game, engine);

            default:
                return null;
        }
    }

    public void setEngine(Engine engine){
        this.engine = engine;
    }
}
