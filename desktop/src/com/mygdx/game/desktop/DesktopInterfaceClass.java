package com.mygdx.game.desktop;

import com.mygdx.game.controller.FirebaseInterface;
import com.mygdx.game.model.components.Player;

import java.util.ArrayList;

public class DesktopInterfaceClass implements FirebaseInterface {

    @Override
    public void initUser() {

    }

    @Override
    public ArrayList<Player> getDataFromDb() {
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
