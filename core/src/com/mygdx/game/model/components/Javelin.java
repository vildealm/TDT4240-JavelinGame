package com.mygdx.game.model.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Javelin extends Actor {
    Texture texture = new Texture(Gdx.files.internal("textures/run/running-1.png"));
    Sprite sprite = new Sprite(texture);

    public Javelin(){
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MoveByAction mba = new MoveByAction();
                mba.setAmount(100f, 0f);
                mba.setDuration(5f);

                Javelin.this.addAction(mba);
                return true;

            }

        });
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);

    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

}
