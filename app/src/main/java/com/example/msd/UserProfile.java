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

    // TextInputEditText fields for username and password input.
    private TextInputEditText nameInput, passwordInput;
    // Database and DAO for accessing user data.
    private UserRoomDatabase db;
    private UserDao userDao;
    // TextView to display login results.
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Link UI elements with their IDs.
        nameInput = findViewById(R.id.name);
        passwordInput = findViewById(R.id.password);
        resultView = findViewById(R.id.result);

        // Initialize database and user DAO.
        db = UserRoomDatabase.getDatabase(this);
        userDao = db.userDao();

        // Setting up navigation buttons.
        ImageButton button1 = findViewById(R.id.button_1id);
        ImageButton button2 = findViewById(R.id.button_2id);
        ImageButton button3 = findViewById(R.id.button_3id);
        ImageButton button4 = findViewById(R.id.button_4id);
        Button signUpButton = findViewById(R.id.button);

        // Configure button listeners for navigating to different activities.
        button1.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(UserProfile.this, UserProfile.class)));

        // Listener for the sign-up button.
        signUpButton.setOnClickListener(v -> startActivity(new Intent(UserProfile.this, RegistrationActivity.class)));

        // Listener for the submit button to perform login.
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(v -> loginUser());
    }

    // Method for user login.
// Reference:  code from https://www.tutorialspoint.com
    private void loginUser() {
        // Get username and password from input fields.
        String username = nameInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Use a separate thread for database query.
        new Thread(() -> {
            // find the user with the given username and password.
            User user = userDao.getUserByUsernameAndPassword(username, password);
            runOnUiThread(() -> {
                if (user != null) {
                    // If user is found, navigate to BMITracker with user data.
                    Intent intent = new Intent(UserProfile.this, BMITracker.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("WEIGHT", user.getWeight());
                    intent.putExtra("HEIGHT", user.getHeight());
                    startActivity(intent);
                    finish();
                } else {
                    // If user is not found, display a failure message.
                    resultView.setText("Login Failed");
                }
            });
        }).start();
    }
}
// End of reference
