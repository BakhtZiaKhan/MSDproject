package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.SharedPreferences;

public class UserProfile extends AppCompatActivity {

    ImageButton button1, button2, button3, button4;
    private TextInputEditText nameInput, weightInput;
    private UserRoomDatabase db;
    private UserDao userDao;
    private TextView resultView; // TextView for displaying the result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialization
        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);
        nameInput = findViewById(R.id.name);
        weightInput = findViewById(R.id.weight);
        resultView = findViewById(R.id.result);

        // Database initialization
        db = Room.databaseBuilder(getApplicationContext(), UserRoomDatabase.class, "user_database").build();
        userDao = db.userDao();

        // Button listeners
        button1.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, UserProfile.class)));

        // Submit button listener
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(v -> saveUserData());
    }

    private void saveUserData() {
        final String name = nameInput.getText().toString();
        float weightValue = 0;
        try {
            weightValue = Float.parseFloat(weightInput.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
            return;
        }

        final float weight = weightValue;
        User newUser = new User(name, weight);

        new Thread(() -> {
            userDao.insertUser(newUser);
            runOnUiThread(() -> {
                displayUserData();
                saveUserDetails(name, weight);
            });
        }).start();
    }

    private void displayUserData() {
        new Thread(() -> {
            User lastUser = userDao.getLastUser();
            runOnUiThread(() -> {
                if (lastUser != null) {
                    String displayText = "Name: " + lastUser.getUsername() + "\nWeight: " + lastUser.getWeight();
                    resultView.setText(displayText);
                } else {
                    resultView.setText("No user data available.");
                }
            });
        }).start();
    }

    private void saveUserDetails(String name, float weight) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.msd.sharedpreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name);
        editor.putFloat("userWeight", weight);
        editor.apply();
    }
}
