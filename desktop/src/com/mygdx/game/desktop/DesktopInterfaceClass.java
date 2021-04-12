package com.mygdx.game.desktop;

import com.mygdx.game.FirebaseInterface;
import com.mygdx.game.Score;

import java.util.ArrayList;

public class DesktopInterfaceClass implements FirebaseInterface {

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
    public void setValueInDb(String username, Double score, String country) {

    }
}
