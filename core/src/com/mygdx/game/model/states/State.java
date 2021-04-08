package com.mygdx.game.model.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    public OrthographicCamera cam;
    public Vector3 mouse;
    public GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void renderScreen(SpriteBatch sb);
    public abstract void dispose();


}


