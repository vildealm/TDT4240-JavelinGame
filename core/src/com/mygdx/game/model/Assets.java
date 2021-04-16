package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Assets {

    private static AssetManager assetManager;
    //Backgrounds
    public static String setupBackground = "textures/backgrounds/mainBackground.png";

    //Player
    public static String player = "textures/run/running-1.png";


    public Assets(){
        assetManager = new AssetManager();
    }

    public static void dispose(){
        assetManager.dispose();
    }

    public static AssetManager getManager(){
        return assetManager;
    }

    public static void load(){
        assetManager.load(setupBackground, Texture.class);
        assetManager.load(player, Texture.class);
    }

    public static boolean update(){
        return assetManager.update();
    }

    public static float getProgress(){
        return assetManager.getProgress();
    }

    public static Texture getTexture(String string){
        return assetManager.get(string, Texture.class);
    }

}
