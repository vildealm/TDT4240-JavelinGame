package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    //Backgrounds
    public static String mainBackground = "textures/backgrounds/background.png"; //Background used outside gameplay
    public static String gameScreenButton = "textures/buttons/playBtn.png"; //Go to Game screen

    private static AssetManager assetManager;

    public Assets(){
        assetManager = new AssetManager();
    }

    public static void dispose(){
        assetManager.dispose();
    }

    public static void load() {
        //Backgrounds
        assetManager.load(mainBackground, Texture.class);
        //Buttons
        assetManager.load(gameScreenButton, Texture.class);
    }
    public static boolean update(){
        return assetManager.update();
    }

    public static float getProgress(){
        return assetManager.getProgress();
    }

    public static Texture getTexture(String tex){
        return assetManager.get(tex, Texture.class);
    }
}

