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

    ImageButton button1;
    private TextInputEditText nameInput;
    private TextInputEditText weightInput;
    private UserRoomDatabase db;
    private UserDao userDao;
    private TextView resultView; // TextView for displaying the result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        button1 = findViewById(R.id.button_1id);
        button1.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, BMITracker.class)));

        // Initialize database and DAO
        db = Room.databaseBuilder(getApplicationContext(),
                UserRoomDatabase.class, "user_database").build();
        userDao = db.userDao();

        // Initialize input fields and result TextView
        nameInput = findViewById(R.id.name);
        weightInput = findViewById(R.id.weight);
        resultView = findViewById(R.id.result);

        // Set up button click listener
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
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

        // Since 'weight' cannot be declared as final due to its modification, we use a separate final variable
        final float weight = weightValue;

        // Create User object
        User newUser = new User(name, weight);

        // Insert data into database in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(newUser);
                // Update UI after saving data
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the TextView to show the name of the user who just submitted
                        resultView.setText("Submitted for: " + name);

                        // Save login state, user's name, and weight in shared preferences
                        saveUserDetails(name, weight); // Save the user's name and weight
                    }
                });
            }
        }).start();
    }

    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.msd.sharedpreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    // Method to save user's name and weight in shared preferences
    private void saveUserDetails(String name, float weight) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.msd.sharedpreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name);
        editor.putFloat("userWeight", weight);
        editor.apply();
    }
}
