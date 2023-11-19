package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class ExerciseActivity extends AppCompatActivity implements ExerciseRVAdapter.ExerciseClickInterface {

    ImageButton button1;
    ImageButton button2;

    ImageButton button3;

private RecyclerView exerciseRV;
private ArrayList<ExerciseRVModal> excerciseRVModalArrayList;
private ExerciseRVAdapter exerciseRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        button1 = (ImageButton) findViewById(R.id.button_1id);
        button2 = (ImageButton) findViewById(R.id.button_2id);
        button3 = (ImageButton) findViewById(R.id.button_3id);

        exerciseRV = findViewById(R.id.idRVExercise);
        excerciseRVModalArrayList = new ArrayList<>();
        exerciseRVAdapter = new ExerciseRVAdapter(excerciseRVModalArrayList, this, this::onExerciseClick);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        exerciseRV.setLayoutManager(manager);
        exerciseRV.setAdapter(exerciseRVAdapter);
        addData();

        button1.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(ExerciseActivity.this, BMITracker.class);
                                           startActivity(intent);

                                       }
                                   }

        );

        button2.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(ExerciseActivity.this,MainActivity .class);
                                           startActivity(intent);

                                       }
                                   }

        );

        button3.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                                           startActivity(intent);

                                       }
                                   }

        );





    }

    private void addData() {
        excerciseRVModalArrayList.add(new ExerciseRVModal("Side Plank",getResources().getString(R.string.side_plank),"https://lottie.host/05fa5a32-dd2c-4f8e-a8cb-e063df6348e7/g1Sm0J7i8m.json",20,10));
        excerciseRVModalArrayList.add(new ExerciseRVModal("Lunges",getResources().getString(R.string.lunges),"https://lottie.host/05fa5a32-dd2c-4f8e-a8cb-e063df6348e7/g1Sm0J7i8m.json",20,10));
        excerciseRVModalArrayList.add(new ExerciseRVModal("Stepping",getResources().getString(R.string.stepping),"https://lottie.host/05fa5a32-dd2c-4f8e-a8cb-e063df6348e7/g1Sm0J7i8m.json",20,10));
        excerciseRVModalArrayList.add(new ExerciseRVModal("Ab Crunches",getResources().getString(R.string.ab_crunch),"https://lottie.host/05fa5a32-dd2c-4f8e-a8cb-e063df6348e7/g1Sm0J7i8m.json",20,10));
        excerciseRVModalArrayList.add(new ExerciseRVModal("Push Ups",getResources().getString(R.string.push_ups),"https://lottie.host/05fa5a32-dd2c-4f8e-a8cb-e063df6348e7/g1Sm0J7i8m.json",20,10));




    }






    @Override
    public void onExerciseClick(int position) {

    }
}
