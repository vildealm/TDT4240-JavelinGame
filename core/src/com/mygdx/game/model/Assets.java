package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Assets {

    private static AssetManager assetManager;
    //Backgrounds
    public static String setupBackground = "textures/backgrounds/mainBackground.png";
    public static String playBackground = "textures/backgrounds/playBackground.png";
    public static String txtfieldBackground = "textures/backgrounds/txtfield.png";
    public static String playerBackground = "textures/backgrounds/playerBox.png";

    //Buttons
    public static String playButton = "buttons/playButton.png";
    public static String throwButton = "buttons/throwButton.png";
    public static String pauseButton = "buttons/pauseButton.png";
    public static String settingsButton = "buttons/settingButton.png";
    public static String HighscoreButton = "buttons/HighscoreButton.png";
    public static String QuitButton = "buttons/quitButton.png";

    public static String onePlayerButton = "onePlayerButton.png";
    public static String twoPlayerButton = "twoPlayerButton.png";
    public static String threePlayerButton = "threePlayerButton.png";
    public static String fourPlayerButton = "fourPlayerButton.png";

    //Sprite
    public static String player = "textures/run/running-1.png";
    public static String javelin = "textures/sprites/javelin.png";


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
        //Background
        assetManager.load(setupBackground, Texture.class);
        assetManager.load(playBackground, Texture.class);
        assetManager.load(player, Texture.class);
        assetManager.load(txtfieldBackground, Texture.class);
        assetManager.load(javelin, Texture.class);
        assetManager.load(playerBackground, Texture.class);

        //MultiplayerSelection buttons
        assetManager.load(onePlayerButton, Texture.class);
        assetManager.load(twoPlayerButton, Texture.class);
        assetManager.load(threePlayerButton, Texture.class);
        assetManager.load(fourPlayerButton, Texture.class);
        //Buttons
        assetManager.load(playButton, Texture.class);
        assetManager.load(throwButton, Texture.class);
        assetManager.load(pauseButton, Texture.class);
        assetManager.load(settingsButton, Texture.class);
        assetManager.load(HighscoreButton, Texture.class);
        assetManager.load(QuitButton, Texture.class);


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
