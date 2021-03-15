package com.mygdx.game.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Scores extends RealmObject {

    @PrimaryKey
    @Required
    private String scoreID;
    @Required
    private Double score;
    @Required
    private String username;

    public Scores(){

    }
    public Scores(String username, Double score){
        this.username = username;
        this.score = score;
    }

    public String getScoreID() {
        return scoreID;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
