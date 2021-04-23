package com.mygdx.game.backend;

import com.mygdx.game.model.components.Score;

import java.util.ArrayList;

public interface FirebaseInterface {

    void initUser();

    ArrayList<Score> getDataFromDb();

    void setValueInDb(String username, Double score, String country);
}
