package com.mygdx.game.model.states;

public interface State {

    void renderScreen();

    void changeState(State state);

    void updateScreen(String type);

    boolean shouldChangeState(String type);
}
   /*public OrthographicCamera cam;
    public Vector3 mouse;
    public GameStateManager gsm;*/

    /*public State(GameStateManager gsm){
        super();
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }*/

    /*public abstract void handleInput();
    public abstract void update(float dt);
/*
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


    public abstract void render(SpriteBatch sb);*/





