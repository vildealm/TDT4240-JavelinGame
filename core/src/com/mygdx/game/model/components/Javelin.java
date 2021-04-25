package com.mygdx.game.model.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.mygdx.game.model.Assets;

public class Javelin extends Actor {

        private Sprite javelinSprite;
        private Vector2 javelinPosition;
        private double velocity = -17.0;

        public Javelin(){
              javelinSprite  = new Sprite(Assets.getTexture(Assets.javelin));
              javelinPosition = new Vector2(550,55);
        }

        @Override
        protected void positionChanged() {

        }

        @Override
        public void draw(Batch batch, float parentAlpha){

        }

        @Override
        public void act(float delta){
            //super.act(delta);
        }

        public void update(){

            }

        public void landedJavelin(Camera camera){
                if (getPosition().y < 12 && getPosition().x > camera.position.x-600  ){
                        velocity = 0;
                }
        }
        public Vector2 getPosition(){
                return this.javelinPosition;
        }

        public Sprite getJavelinSprite(){
                return this.javelinSprite;
        }
        public double getVelocity(){
                return this.velocity;
        }

        public void setPositionX(float pos){
                this.javelinPosition.x = pos;
        }
        public void setPositionY(float pos){
                this.javelinPosition.y = pos;
        }

        public void setSpriteRotation(float x){
                javelinSprite.setRotation(x);
        }
        public void setVelocity(double v){
                this.velocity = v;
        }

        public Vector2 updateJavelinPosition(boolean normalThrow, int posX, int cameraLimit, double deltaTime){
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

                setPositionX((float)(getPosition().x - (getVelocity() * deltaTime)));
                setPositionY((float) (getPosition().y - (getVelocity() * deltaTime)));

                if (getPosition().x > upLimit) {
                        getJavelinSprite().setRotation(0);
                        setPositionY((float) (getPosition().y - (-getVelocity() * deltaTime)));
                }
                if ((getPosition().x > downLimit) ) {
                        setPositionY((float) (getPosition().y - (-getVelocity() * deltaTime)));
                        setSpriteRotation(-30);
                }

                return getPosition();
        }


}

