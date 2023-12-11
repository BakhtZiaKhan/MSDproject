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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor stepSensor;
    private int totalSteps = 0;
    private int previewsTotalSteps = 0;
    private TextView steps;
    private ProgressBar progressBar;
    private TextView userNameTextView;

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        resetSteps();
        loadData();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepSensor == null) {
            Toast.makeText(this, "This device has no step counter sensor", Toast.LENGTH_SHORT).show();
        }

        displayUserName();
    }

    private void initViews() {
        button1 = findViewById(R.id.button_1id);
        button2 = findViewById(R.id.button_2id);
        button3 = findViewById(R.id.button_3id);
        button4 = findViewById(R.id.button_4id);
        progressBar = findViewById(R.id.progressBar);
        steps = findViewById(R.id.steps);
        userNameTextView = findViewById(R.id.userNameTextView);

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BMITracker.class);
            intent.putExtra("USERNAME", getLoggedInUsername());
            startActivity(intent);
        });
        button2.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        button3.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ExerciseActivity.class)));
        button4.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserProfile.class)));
    }

    private void displayUserName() {
        userNameTextView.setText(getLoggedInUsername());
    }

    private String getLoggedInUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("LoggedInUser", "No User");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = (int) event.values[0];
            int currentSteps = totalSteps - previewsTotalSteps;
            steps.setText(String.valueOf(currentSteps));
            progressBar.setProgress(currentSteps);
        }
    }

    private void resetSteps() {
        steps.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Long press to reset steps", Toast.LENGTH_SHORT).show());
        steps.setOnLongClickListener(v -> {
            previewsTotalSteps = totalSteps;
            steps.setText("0");
            saveData();
            return true;
        });
    }

    private void saveData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("key1", (float) previewsTotalSteps);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        float savedNumber = sharedPref.getFloat("key1", 0f);
        previewsTotalSteps = (int) savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }
}
