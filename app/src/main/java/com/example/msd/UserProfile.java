package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {

    private TextInputEditText nameInput, passwordInput;
    private UserRoomDatabase db;
    private UserDao userDao;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        nameInput = findViewById(R.id.name);
        passwordInput = findViewById(R.id.password);
        resultView = findViewById(R.id.result);

        db = UserRoomDatabase.getDatabase(this);
        userDao = db.userDao();

        ImageButton button1 = findViewById(R.id.button_1id);
        ImageButton button2 = findViewById(R.id.button_2id);
        ImageButton button3 = findViewById(R.id.button_3id);
        ImageButton button4 = findViewById(R.id.button_4id);
        Button signUpButton = findViewById(R.id.button);

        // Setting up button listeners for navigation
        button1.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, UserProfile.class)));

        // Listener for the Sign Up button
        signUpButton.setOnClickListener(v -> startActivity(new Intent(UserProfile.this, RegistrationActivity.class)));

        // Listener for the Submit button to perform login
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = nameInput.getText().toString();
        String password = passwordInput.getText().toString();

        new Thread(() -> {
            User user = userDao.getUserByUsernameAndPassword(username, password);
            runOnUiThread(() -> {
                if (user != null) {
                    // If login is successful, pass user data to BMITracker
                    Intent intent = new Intent(UserProfile.this, BMITracker.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("WEIGHT", user.getWeight());
                    intent.putExtra("HEIGHT", user.getHeight());
                    startActivity(intent);
                    finish();
                } else {
                    // If login fails, show a failure message
                    resultView.setText("Login Failed");
                }
            });
        }).start();
    }
}
