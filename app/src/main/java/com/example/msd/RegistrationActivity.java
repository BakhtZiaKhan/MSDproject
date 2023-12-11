package com.example.msd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, weightEditText, heightEditText;
    private UserRoomDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);

        db = UserRoomDatabase.getDatabase(this);
        userDao = db.userDao();

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        float weight;
        float height;

        try {
            weight = Float.parseFloat(weightEditText.getText().toString());
            height = Float.parseFloat(heightEditText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User existingUser = userDao.findUserByUsername(username);
            if (existingUser == null) {
                User newUser = new User(username, weight, height, password);
                userDao.insertUser(newUser);
                saveLoggedInUsername(username);
                runOnUiThread(() -> {
                    Toast.makeText(RegistrationActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(RegistrationActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    private void saveLoggedInUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoggedInUser", username);
        editor.apply();
    }
}
