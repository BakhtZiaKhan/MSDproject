package com.example.msd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Activity for handling user registration.

public class RegistrationActivity extends AppCompatActivity {

    // EditText fields for user input.
    private EditText usernameEditText, passwordEditText, weightEditText, heightEditText;
    // Database and DAO for user data handling.
    private UserRoomDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Linking the EditText fields to the UI elements.
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);

        // Initializing database and user DAO.
        db = UserRoomDatabase.getDatabase(this);
        userDao = db.userDao();

        // Setting up the submit button and its click listener.
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> registerUser());

        // Setting up the back button to navigate to the previous screen.
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            // Navigate back to the previous screen (MainActivity in this case).
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Method to handle user registration.
    // Reference : Code was closely based of https://www.youtube.com/watch?v=0QqAkopW31M
    private void registerUser() {
        // Extracting user input from EditText fields.
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        float weight;
        float height;
// End reference
        try {
            // Parsing numerical inputs for weight and height.
            weight = Float.parseFloat(weightEditText.getText().toString());
            height = Float.parseFloat(heightEditText.getText().toString());
        } catch (NumberFormatException e) {
            // Show error message if input is invalid.
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        // Using a separate thread for database operations.
        new Thread(() -> {
            // Checking if the user already exists in the database.
            User existingUser = userDao.findUserByUsername(username);
            if (existingUser == null) {
                // Creating a new user and inserting it into the database.
                User newUser = new User(username, weight, height, password);
                userDao.insertUser(newUser);
                // Saving the username in SharedPreferences.
                saveLoggedInUsername(username);
                // Navigate to the MainActivity on successful registration.
                runOnUiThread(() -> {
                    Toast.makeText(RegistrationActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            } else {
                // Notify if the user already exists.
                runOnUiThread(() -> {
                    Toast.makeText(RegistrationActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    // Method to save the logged-in username in SharedPreferences.
    private void saveLoggedInUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedInUser", username);
        editor.apply();
    }
}
