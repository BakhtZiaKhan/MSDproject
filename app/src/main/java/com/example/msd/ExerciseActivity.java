package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// This class is for the Exercise Activity in our app.
// Reference: The following code is from https://www.geeksforgeeks.org/videos/how-to-create-health-fitness-application-in-android/
public class ExerciseActivity extends AppCompatActivity implements ExerciseRVAdapter.ExerciseClickInterface {

    // Initializing the image buttons for navigation.
    ImageButton button1, button2, button3, button4;

    // RecyclerView and its adapter for displaying exercises.
    private RecyclerView exerciseRV;
    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private ExerciseRVAdapter exerciseRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        // Linking the buttons with the layout.
        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);

        // Setting up the RecyclerView with its adapter and layout manager.
        exerciseRV = findViewById(R.id.idRVExercise);
        exerciseRVModalArrayList = new ArrayList<>();
        exerciseRVAdapter = new ExerciseRVAdapter(exerciseRVModalArrayList, this, this);
        exerciseRV.setLayoutManager(new LinearLayoutManager(this));
        exerciseRV.setAdapter(exerciseRVAdapter);

        // Populating the exercise list.
        addData();
        // Reference complete

        // Setting up button click listeners for navigation between activities.
        button1.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(ExerciseActivity.this, UserProfile.class)));
    }

    // Method to add exercises to the RecyclerView.
    private void addData() {
        exerciseRVModalArrayList.add(new ExerciseRVModal("Side Plank", getResources().getString(R.string.side_plank), R.drawable.sideplank, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Lunges", getResources().getString(R.string.lunges), R.drawable.lungs, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Stepping", getResources().getString(R.string.stepping), R.drawable.stepping, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Ab Crunches", getResources().getString(R.string.ab_crunch), R.drawable.crunches, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Push Ups", getResources().getString(R.string.push_ups), R.drawable.pushups, 20, 10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Squats", getResources().getString(R.string.squats), R.drawable.squats, 20, 10));

        // Notify the adapter to refresh the UI.
        exerciseRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseClick(int position) {


    }
}
