package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Assets {

    private static AssetManager assetManager;
    //Backgrounds
    public static String setupBackground = "textures/backgrounds/mainBackground.png";
    public Texture textureBackground = new Texture(setupBackground);
    public static String playBackground = "textures/backgrounds/playBackground.png";
    public Texture texturePlayBackground = new Texture(playBackground);
    public static String txtfieldBackground = "textures/backgrounds/txtfield.png";

    //Buttons
    public static String playButton = "buttons/playButton.png";
    public static String throwButton = "buttons/throwButton.png";
    public static String pauseButton = "buttons/pauseButton.png";
    public static String settingsButton = "buttons/settingButton.png";




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
        assetManager.load(playBackground, Texture.class);
        assetManager.load(player, Texture.class);
        assetManager.load(txtfieldBackground, Texture.class);
        assetManager.load(playButton, Texture.class);
        assetManager.load(throwButton, Texture.class);
        assetManager.load(pauseButton, Texture.class);
        assetManager.load(settingsButton, Texture.class);

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
