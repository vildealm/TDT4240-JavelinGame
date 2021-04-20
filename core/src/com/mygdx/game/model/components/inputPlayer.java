package com.mygdx.game.model.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.model.Assets;

public class inputPlayer extends Actor {

    Label username;
    Label country;
    Label errorMsg;
    TextField textfield;
    SelectBox sb;

    //Sprite
    Texture txtSprite = Assets.getTexture(Assets.txtfieldBackground);
    BitmapFont font = new BitmapFont();


    public inputPlayer(int xPosition){
        this.sb = makeDropdown(xPosition + 150);
        this.textfield = makeInputField(xPosition + 150);
        this.country = makeLabel("Choose Country",Color.BLACK, xPosition, 300);
        this.username = makeUsernameLabel(Color.BLACK, xPosition, 350);
        this.errorMsg = makeLabel("Unvalid Inputs",Color.BLACK, 900, 500);
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

    //Checks if username is correctly written // HVORDAN SJEKKER MAN LIKE BRUKERNAVN????
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
        tf.setSize(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/28f);
        tf.setPosition(xPos,350);
        return tf;
    }

    private Label makeLabel(String txt, Color color, int xPos, int yPos){
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), color);
        Label country = new Label(txt, style);
        country.setPosition(xPos,yPos);
        country.setFontScale(1.3f);
        return country;
    }

    private Label makeUsernameLabel(Color color, int xPos, int yPos){
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), color);
        Label username = new Label("Username: ", style);
        username.setPosition(xPos,yPos);
        username.setFontScale(1.3f);
        return username;
    }

    private SelectBox makeDropdown(int xPos){
        SpriteDrawable s1 = new SpriteDrawable(new Sprite(txtSprite));
        ScrollPane.ScrollPaneStyle sp = new ScrollPane.ScrollPaneStyle();
        List.ListStyle ls = new List.ListStyle(font, Color.WHITE, Color.BLACK, s1);
        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle(font, Color.BLACK, s1, sp, ls);
        SelectBox<String> selectBox = new SelectBox<>(boxStyle);
        String[] countries = new String[]{"---","NOR","SVE","DAN","FIN"};
        selectBox.setItems(countries);
        selectBox.setPosition(xPos, 300);
        selectBox.setSize(110, 37);
        return selectBox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        this.sb.draw(batch, 1);
        this.username.draw(batch,1);
        this.textfield.draw(batch, 1);
        this.country.draw(batch,1);
    }
}


