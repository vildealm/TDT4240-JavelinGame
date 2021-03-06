package com.mygdx.game.controller;
import com.mygdx.game.JavelinGame;
//import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.model.Assets;
import com.mygdx.game.view.EndScreen;
import com.mygdx.game.view.HighScoreScreen;
import com.mygdx.game.view.LearnScreen;
import com.mygdx.game.view.LoadingScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.JavelinGame;
//import com.mygdx.game.view.LoadingScreen;
import com.mygdx.game.model.states.GameStateManager;
import com.mygdx.game.view.MenuScreen;
import com.mygdx.game.view.MultiplayerSelectionScreen;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.Screen2;
import com.mygdx.game.view.SetupScreen;

public class ScreenFactory {
    private static GameStateManager gsm;
    private static Assets assets;

    public ScreenFactory(GameStateManager gsm, Assets assets){
        this.gsm = gsm;
        this.assets = assets;
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("ScreenFactorygsm", String.valueOf(gsm.getStates()));
    }

    public static Screen2 getScreen(String screenType){
        switch (screenType){
            case "LOADING":
                return new LoadingScreen(gsm,assets);
            case "MENU":
                return new MenuScreen(gsm);
            case "PLAY":
                return new PlayScreen(gsm);
            case "SETUP":
                return new SetupScreen(gsm);
            case "END":
                return new EndScreen(gsm);
            case "HIGHSCORE":
                return new HighScoreScreen(gsm);
            case "MULTIPLAYER":
                return new MultiplayerSelectionScreen(gsm);
            case "LEARN":
                return new LearnScreen(gsm);
            default:
                return null;


        }
    }
}
