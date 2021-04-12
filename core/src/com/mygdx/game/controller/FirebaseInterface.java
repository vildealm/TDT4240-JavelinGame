package com.mygdx.game.controller;

import com.mygdx.game.model.components.Score;

import java.util.ArrayList;

public interface FirebaseInterface {

    public void initUser();

    public ArrayList<Score> getDataFromDb();

    public void setOnValueChangedListener();

    public int getUserPos();

    public void setValueInDb(String username, Double score, String country);
}
