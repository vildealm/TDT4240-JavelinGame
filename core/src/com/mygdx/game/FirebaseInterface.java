package com.mygdx.game;

import java.util.ArrayList;

public interface FirebaseInterface {

    public void initUser();

    public ArrayList<Score> getDataFromDb();

    public void setOnValueChangedListener();

    public int getUserPos();

    public void setValueInDb(String username, Double score, String country);
}
