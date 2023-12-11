package com.example.msd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class BMITracker extends AppCompatActivity {

    ImageButton button1, button2, button3, button4;
    EditText editTextHeight;
    TextView textView, weightDisplay, userNameTextView;
    private UserRoomDatabase db;
    private UserDao userDao;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tracker);

        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);
        editTextHeight = findViewById(R.id.height);
        textView = findViewById(R.id.result);
        weightDisplay = findViewById(R.id.weight);
        userNameTextView = findViewById(R.id.userNameTextView);

        db = Room.databaseBuilder(getApplicationContext(), UserRoomDatabase.class, "user_database").build();
        userDao = db.userDao();

        Intent intent = getIntent();
        float weight = intent.getFloatExtra("WEIGHT", 0);
        float height = intent.getFloatExtra("HEIGHT", 0);

        if (weight != 0 && height != 0) {
            weightDisplay.setText(String.format("%.2f kg", weight));
            editTextHeight.setText(String.format("%.2f", height));
        }

        displayUserName();
        setupButtonListeners();
        calculateBMI();
    }

    private void displayUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String loggedInUser = sharedPreferences.getString("LoggedInUser", "No User");
        userNameTextView.setText(loggedInUser);
    }

    private void calculateBMI() {
        Button button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(view -> {
            float weight = Float.parseFloat(weightDisplay.getText().toString().replace(" kg", ""));
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
