package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.components.Javelin;

public class JavelinController {

    private double deltaTime = 0.3;

    public JavelinController(){

    }

    public Vector2 updateJavelinPosition(Javelin javelin, boolean normalThrow, int posX, int cameraLimit){
        int upLimit;
        int downLimit;
        if (normalThrow) {
            upLimit = 800;
            downLimit = cameraLimit - 500;
        }
        else {
            if (posX == 20) {
                upLimit = posX + 240;
                downLimit = posX + 310;
            }
            else {
                upLimit = posX + 300;
                downLimit = posX + 350;
            }
        }

        javelin.setPositionX((float)(javelin.getPosition().x - (javelin.getVelocity() * deltaTime)));
        javelin.setPositionY((float) (javelin.getPosition().y - (javelin.getVelocity() * deltaTime)));

        if (javelin.getPosition().x > upLimit) {
            javelin.getJavelinSprite().setRotation(0);
            javelin.setPositionY((float) (javelin.getPosition().y - (-javelin.getVelocity() * deltaTime)));
        }
        if ((javelin.getPosition().x > downLimit) ) {
            javelin.setPositionY((float) (javelin.getPosition().y - (-javelin.getVelocity() * deltaTime)));
            javelin.setSpriteRotation(-30);
        }

        return javelin.getPosition();
    }

}
