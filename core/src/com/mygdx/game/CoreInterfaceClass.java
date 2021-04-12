package com.mygdx.game;

import java.util.ArrayList;

public class CoreInterfaceClass implements FirebaseInterface {

    @Override
    public void initUser() {

    }

    @Override
    public ArrayList<Score> getDataFromDb() {
        return null;
    }

    @Override
    public void setOnValueChangedListener() {

    }

    @Override
    public int getUserPos() {
        return 0;
    }

    @Override
    public void setValueInDb(String username, Double score, String country) {

    }
}
