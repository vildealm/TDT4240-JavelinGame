package com.mygdx.game.controller;

import com.mygdx.game.JavelinGame;
//import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.view.MenuScreen;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.Screen2;
import com.mygdx.game.view.SetupScreen;

public class ScreenFactory {
    public static JavelinGame game = JavelinGame.getInstance();
    //public static Engine engine = new Engine();


    public static Screen2 getScreen(String screenType){
        switch (screenType){
            case "LOADING":
                return new LoadingScreen(game);
            case "MENU":
                return new MenuScreen(game);
            case "PLAY":
                return new PlayScreen(game);
            case "SETTING":
                return new SetupScreen(game);
            default:
                return null;
        }
    }

    /*public void setEngine(Engine engine){
        this.engine = engine;
    }*/
}
