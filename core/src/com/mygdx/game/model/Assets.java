package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Assets {

    private static AssetManager assetManager;
    //Backgrounds
    public static String setupBackground = "textures/backgrounds/mainBackground.png";
    public static String txtfieldBackground = "textures/backgrounds/txtfield.png";

    //Players
    public static String player1 = "textures/run/running-1.png";
    public static String player2 = "textures/run/running-2.png";
    public static String player3 = "textures/run/running-3.png";
    public static String player4 = "textures/run/running-4.png";


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
        assetManager.load(player1, Texture.class);
        assetManager.load(player2, Texture.class);
        assetManager.load(player3, Texture.class);
        assetManager.load(player4, Texture.class);
        assetManager.load(txtfieldBackground, Texture.class);
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
