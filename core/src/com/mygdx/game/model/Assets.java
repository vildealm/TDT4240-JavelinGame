package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


public class Assets {

    private static AssetManager assetManager;
    //Backgrounds
    public static String setupBackground = "textures/backgrounds/mainBackground.png";
    public Texture textureBackground = new Texture(setupBackground);
    public static String playBackground = "textures/backgrounds/playBackground.png";
    public Texture texturePlayBackground = new Texture(playBackground);


    /*public static String characterBackground = "";

    //TextField
    public static String textFieldBackground = ""; //Used to make TextField visible

    //Buttons
    public static String playButton = ""; // Used in HighscoreScreen to go to ShopScreen
    public static String addPlayerButton = ""; //Used in GameScreen to go to SettingScreen

    //Textfields
    public static String setupsTitle =  ""; //Title for settings page

    //Sprites
    public static String player1 = "";
    public static String player2 = "";
    public static String player3 = "";
    public static String player4 = "";*/

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
