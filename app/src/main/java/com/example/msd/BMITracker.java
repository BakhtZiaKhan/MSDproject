package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class BMITracker extends AppCompatActivity {

    ImageButton button1, button2, button3,button4;


    EditText editTextHeight;
    TextView textView, weightDisplay;
    private UserRoomDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tracker);

        // Initialize UI components
        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);
        editTextHeight = findViewById(R.id.height);
        textView = findViewById(R.id.result);
        weightDisplay = findViewById(R.id.weight);

        // Initialize database and DAO
        db = Room.databaseBuilder(getApplicationContext(), UserRoomDatabase.class, "user_database").build();
        userDao = db.userDao();

        // Fetch the last entered weight from the database
        fetchAndDisplayWeight();

        // Set up button click listeners
        setupButtonListeners();
    }

    private void fetchAndDisplayWeight() {
        new Thread(() -> {
            User lastUser = userDao.getLastUser();
            if (lastUser != null) {
                float weight = lastUser.getWeight();
                runOnUiThread(() -> {
                    weightDisplay.setText(String.format("%.2f kg", weight));
                    calculateAndDisplayBMI(weight);
                });
            }
        }).start();
    }

    private void calculateAndDisplayBMI(float weight) {
        Button button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(view -> {
            float height = Float.parseFloat(editTextHeight.getText().toString()) / 100;
            float bmi = weight / (height * height);
            textView.setText(String.format("BMI: %.2f", bmi));
        });
    }

    private void setupButtonListeners() {
        button1.setOnClickListener(view -> startActivity(new Intent(BMITracker.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(BMITracker.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(BMITracker.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(BMITracker.this, UserProfile.class)));
    }
}
