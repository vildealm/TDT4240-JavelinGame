package com.mygdx.game;

import androidx.annotation.NonNull;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.controller.FirebaseInterface;
import com.mygdx.game.model.components.Score;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class AndroidInterfaceClass implements FirebaseInterface {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private UUID id;
    private ArrayList<Score> highscores= new ArrayList<>();

    public AndroidInterfaceClass(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("highscore");
    }

    //Initializes user by logging in
    @Override
    public void initUser() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
        user=mAuth.getCurrentUser();
    }

    //Collects the data from the database.
    //Query sorts the data, and collects the 10 last objects (the ones who have thrown the longest)
    @Override
    public ArrayList<Score> getDataFromDb() {
        Query sortedData = myRef.orderByChild("score").limitToLast(10);
        sortedData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Score score = postSnapshot.getValue(Score.class);
                    highscores.add(score);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return highscores;
    }

    @Override
    public void setOnValueChangedListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public int getUserPos() {
        /*Query sortedData = myRef.orderByChild("score");
        sortedData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    if(postSnapshot.child(String.valueOf(id)).getKey().equals(id.toString())){
                        Log.d(TAG, "Found you!");

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        return 0;

    }

    //Function for posting data to database
    @Override
    public void setValueInDb(String username, Double score, String country) {
        myRef = database.getReference("highscore");
        id = UUID.randomUUID();
        if(mAuth.getCurrentUser()!= null){
            myRef.child(id.toString()).child("username").setValue(username);
            myRef.child(id.toString()).child("score").setValue(score);
            myRef.child(id.toString()).child("country").setValue(country);
        }
    }
}