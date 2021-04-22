package com.mygdx.game.model.components;

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

public class inputPlayer extends Actor {

    private Label username, country, errorMsg, idTxt;
    private TextField textfield;
    private SelectBox sb;
    private String id;

    private Texture txtSprite = Assets.getTexture(Assets.txtfieldBackground);

    BitmapFont font = new BitmapFont();


    public inputPlayer(int xPosition, int id){
        this.sb = makeDropdown(xPosition + 150);
        this.textfield = makeInputField(xPosition + 150);
        this.country = makeLabel("Choose Country",Color.BLACK, xPosition, 390);
        this.username = makeLabel("Username", Color.BLACK, xPosition, 440);
        this.idTxt = makeLabel("Player " + id, Color.BLACK, xPosition+80, 510);
        this.errorMsg = makeLabel("Invalid Inputs",Color.BLACK, 900, 500);
    }


    public TextField getTextfield(){
        return textfield;
    }
    public SelectBox getSelectbox(){
        return sb;
    }

    public String getUsername(){
        return textfield.getText();
    }
    public String getCountry(){
        return sb.getSelected().toString();
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

    private TextField makeInputField(int xPos){
        SpriteDrawable s = new SpriteDrawable(new Sprite(txtSprite));
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(new BitmapFont(), Color.BLACK,null,null,s);
        TextField tf = new TextField("", style);
        tf.setSize(90, 27);
        tf.setPosition(xPos,440);
        return tf;
    }

    public Label makeLabel(String txt, Color color, int xPos, int yPos){
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), color);
        Label country = new Label(txt, style);
        country.setPosition(xPos,yPos);
        country.setFontScale(1.3f);
        return country;
    }

    private SelectBox makeDropdown(int xPos){
        SpriteDrawable s1 = new SpriteDrawable(new Sprite(txtSprite));
        ScrollPane.ScrollPaneStyle sp = new ScrollPane.ScrollPaneStyle();
        List.ListStyle ls = new List.ListStyle(font, Color.WHITE, Color.BLACK, s1);
        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle(font, Color.BLACK, s1, sp, ls);
        SelectBox<String> selectBox = new SelectBox<>(boxStyle);
        String[] countries = new String[]{"---","NOR","SVE","DAN","FIN"};
        selectBox.setItems(countries);
        selectBox.setPosition(xPos, 385);
        selectBox.setSize(90, 27);
        return selectBox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        this.sb.draw(batch, 1);
        this.username.draw(batch,1);
        this.textfield.draw(batch, 1);
        this.country.draw(batch,1);
        this.idTxt.draw(batch, 1);
    }
}


