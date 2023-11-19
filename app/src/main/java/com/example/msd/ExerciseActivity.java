package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ExerciseActivity extends AppCompatActivity {

private RecyclerView exerciseRV;
private ArrayList<ExerciseRVModal> excerciseRVModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        exerciseRV = findViewById(R.id.idRVExercise);
        excerciseRVModalArrayList = new ArrayList<ExerciseRVModal>();




}




}
