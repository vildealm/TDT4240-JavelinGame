package com.mygdx.game.model.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.view.Screen2;

public abstract class State {
    public GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void renderScreen();
    public abstract void dispose();
    public abstract Screen2 getScreen();



}


