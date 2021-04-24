package com.mygdx.game.model.components;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.model.Assets;

public class PlayerInputBox extends Actor {

    //Label, Textfield and SelectBox
    private Label username, country, errorMsg, idTxt;
    private TextField textfield;
    private SelectBox selectBox;
    //Textfield background
    private Texture txtSprite = Assets.getTexture(Assets.txtfieldBackground);

    public PlayerInputBox(int xPosition, int id){
        this.selectBox = makeDropdown(xPosition + Gdx.graphics.getWidth()/7);
        this.textfield = makeInputField(xPosition + Gdx.graphics.getWidth()/8);
        this.country = makeLabel("Choose Country",Color.BLACK, xPosition, ((Gdx.graphics.getHeight()*9)/14));
        this.username = makeLabel("Username", Color.BLACK, xPosition, ((Gdx.graphics.getHeight()*7)/10));
        this.idTxt = makeLabel("Player " + id, Color.BLACK, xPosition+80, ((Gdx.graphics.getHeight()*12)/15));
        this.errorMsg = makeLabel("Invalid Inputs",Color.BLACK, (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/30), Gdx.graphics.getHeight()/3);
    }


    public TextField getTextfield(){
        return textfield;
    }
    public SelectBox getSelectbox(){
        return selectBox;
    }

    public String getUsername(){
        return textfield.getText();
    }
    public String getCountry(){
        return selectBox.getSelected().toString();
    }
    public Label getErrorMsg(){
        return errorMsg;
    }
    public Label getIdTxt(){
        return idTxt;
    }

    //Checks if username is correctly written
    public boolean checkInputFields() {
        if (!getUsername().isEmpty() &&
                !getUsername().contains("æ") &&
                !getUsername().contains("ø") &&
                !getUsername().contains("å") &&
                !getUsername().contains(" ") &&
                getUsername().length() < 15 &&
                !getCountry().contains("---")) {
            return true;
        } else {
            return false;
        }
    }

    //Makes inputfield with parameter xPos that is defines in SetupScreen
    private TextField makeInputField(int xPos){
        SpriteDrawable s = new SpriteDrawable(new Sprite(txtSprite));
        BitmapFont font = new BitmapFont();
        font.getData().setScale(Gdx.graphics.getWidth()/800f);
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(font, Color.BLACK,null,null,s);
        TextField tf = new TextField("", style);
        tf.setSize(Gdx.graphics.getWidth()/12 , Gdx.graphics.getWidth()/36);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("#INFO", String.valueOf(Gdx.graphics.getHeight()));
        tf.setPosition(xPos,(Gdx.graphics.getHeight()*7)/10);
        return tf;
    }

    public Label makeLabel(String txt, Color color, int xPos, int yPos){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(Gdx.graphics.getWidth()/800f);
        Label.LabelStyle style = new Label.LabelStyle(font, color);
        Label country = new Label(txt, style);
        country.setPosition(xPos,yPos);
        return country;
    }

    private SelectBox makeDropdown(int xPos){
        SpriteDrawable s1 = new SpriteDrawable(new Sprite(txtSprite));
        ScrollPane.ScrollPaneStyle sp = new ScrollPane.ScrollPaneStyle();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(Gdx.graphics.getWidth()/800f);
        List.ListStyle ls = new List.ListStyle(font, Color.WHITE, Color.BLACK, s1);
        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle(font, Color.BLACK, s1, sp, ls);
        SelectBox<String> selectBox = new SelectBox<>(boxStyle);
        String[] countries = new String[]{"---","NOR","SWE","DAN", "USA", "BRA", "GER", "RUS", "AUS"};
        selectBox.setItems(countries);
        selectBox.setPosition(xPos, ((Gdx.graphics.getHeight()*9)/14));
        selectBox.setSize(Gdx.graphics.getWidth()/15f, Gdx.graphics.getWidth()/37);
        return selectBox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        this.selectBox.draw(batch, 1);
        this.username.draw(batch,1);
        this.textfield.draw(batch, 1);
        this.country.draw(batch,1);
        this.idTxt.draw(batch, 1);
    }
}


