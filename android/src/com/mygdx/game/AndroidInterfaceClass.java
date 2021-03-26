package com.mygdx.game;

import androidx.annotation.NonNull;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class AndroidInterfaceClass implements FirebaseInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidInterfaceClass(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
    }

    @Override
    public void SomeFunction() {
        System.out.println("Just some function");
    }

    @Override
    public void FirstFirebaseTest() {
        if(myRef != null){
            myRef.setValue("Hello, World!");
        }
        else{
            System.out.println("Database reference was not set, therefore could not write to database");
        }
    }

    @Override
    public void SetOnValueChangedListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDb(String target, String value) {
        myRef = database.getReference(target);
        myRef.setValue(value);
    }
}
