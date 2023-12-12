package com.example.msd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Main activity for our fitness app, responsible for step counting.

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // SensorManager to manage the device's sensors.
    private SensorManager mSensorManager;
    private Sensor stepSensor; // The step counter sensor.
    private int totalSteps = 0; // Total steps since the sensor was activated.
    private int previewsTotalSteps = 0; // Step count before the app was opened.
    private TextView steps; // TextView to display the current step count.
    private ProgressBar progressBar; // ProgressBar to show progress visually.
    private TextView userNameTextView; // TextView to show the logged-in user's name.

    // Image buttons for navigation within the app.
    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements.
        initViews();
        resetSteps(); // Setup for resetting step count.
        loadData(); // Load previously saved step count.

        // Initialize the SensorManager and step sensor.
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepSensor == null) {
            Toast.makeText(this, "No step counter sensor available on this device.", Toast.LENGTH_SHORT).show();
        }

        // Display the logged-in user's name.
        displayUserName();
    }

    // Method to initialize UI components and set click listeners.
    private void initViews() {
        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);
        progressBar = findViewById(R.id.progressBar);
        steps = findViewById(R.id.steps);
        userNameTextView = findViewById(R.id.userNameTextView);

        // Setup click listeners for navigation buttons.
        button1.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BMITracker.class)));
        button2.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserProfile.class)));
    }

    // Display the username of the currently logged-in user.
    private void displayUserName() {
        userNameTextView.setText(getLoggedInUsername());
    }

    // Retrieve the username of the currently logged-in user.
    private String getLoggedInUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("LoggedInUser", "No User");
    }
    // Reference : The following code is from https://www.youtube.com/watch?v=OUpR1a_9G98&t=131s
    // Register the sensor listener when the activity resumes.
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Unregister the sensor listener when the activity pauses.
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    // Handle changes to the step sensor.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = (int) event.values[0];
            int currentSteps = totalSteps - previewsTotalSteps;
            steps.setText(String.valueOf(currentSteps));
            progressBar.setProgress(currentSteps);
        }
    }

    // Setup for resetting the step counter.
    private void resetSteps() {
        steps.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Long press to reset steps", Toast.LENGTH_SHORT).show());
        steps.setOnLongClickListener(v -> {
            previewsTotalSteps = totalSteps;
            steps.setText("0");
            saveData();
            return true;
        });
    }

    // Save the current step count.
    private void saveData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("key1", (float) previewsTotalSteps);
        editor.apply();
    }

    // Load the saved step count.
    private void loadData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        float savedNumber = sharedPref.getFloat("key1", 0f);
        previewsTotalSteps = (int) savedNumber;
    }

    // Method required for implementing SensorEventListener, not used here.
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used but required to implement SensorEventListener.
    }
}
// Reference complete