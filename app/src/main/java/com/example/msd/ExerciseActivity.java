package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRVAdapter.ExerciseClickInterface {

    ImageButton button1, button2, button3, button4;
    private RecyclerView exerciseRV;
    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private ExerciseRVAdapter exerciseRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);

        exerciseRV = findViewById(R.id.idRVExercise);
        exerciseRVModalArrayList = new ArrayList<>();
        exerciseRVAdapter = new ExerciseRVAdapter(exerciseRVModalArrayList, this, this);
        exerciseRV.setLayoutManager(new LinearLayoutManager(this));
        exerciseRV.setAdapter(exerciseRVAdapter);
        addData();

        button1.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, UserProfile.class)));
    }

    private void addData() {
        exerciseRVModalArrayList.add(new ExerciseRVModal("Side Plank", getResources().getString(R.string.side_plank), R.drawable.sideplank, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Lunges", getResources().getString(R.string.lunges), R.drawable.lungs, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Stepping", getResources().getString(R.string.stepping), R.drawable.stepping, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Ab Crunches", getResources().getString(R.string.ab_crunch), R.drawable.crunches, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Push Ups", getResources().getString(R.string.push_ups), R.drawable.pushups, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Squats", getResources().getString(R.string.squats), R.drawable.squats, 20, 10));

        exerciseRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseClick(int position) {
        // Handle the click event if needed
    }
}
