package com.mygdx.game.controller;
import com.mygdx.game.JavelinGame;
//import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.view.LoadingScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.JavelinGame;
//import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.view.MenuScreen;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.Screen2;
import com.mygdx.game.view.SetupScreen;

public class ScreenFactory {
    private static GameStateManager gsm;


    public ScreenFactory(GameStateManager gsm){
        this.gsm = gsm;
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("ScreenFactorygsm", String.valueOf(gsm.getStates()));
    }
    //public static Engine engine = new Engine();


    public static Screen2 getScreen(String screenType){
        switch (screenType){
            case "LOADING":
                return new LoadingScreen(gsm);
            case "MENU":
                return new MenuScreen(gsm);
            case "PLAY":
                return new PlayScreen(gsm);
            case "SETTING":
                return new SetupScreen(gsm);
            default:
                return null;
        }
    }

    /*public void setEngine(Engine engine){
        this.engine = engine;
    }*/
}
