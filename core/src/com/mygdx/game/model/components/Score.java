package com.mygdx.game.model.components;

public class Score {

    private String username;
    private Double score;
    private String country;

    public Score(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Score(String username, Double score, String country){
        this.username = username;
        this.score = score;
        this.country = country;
    }
}
