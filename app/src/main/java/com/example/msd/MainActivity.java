package com.example.msd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
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

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button_1id);

        progressBar = findViewById(R.id.progressBar);

        steps = findViewById(R.id.steps);

        resetSteps();
        loadData();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        button1.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(MainActivity.this, BMITracker.class);
                                           startActivity(intent);

                                       }
                                   }

        );

    }

    protected void onResume() {
        super.onResume();

        if (stepSensor == null) {
            Toast.makeText(this, "This device has no sensor", Toast.LENGTH_SHORT).show();
        } else {
            mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


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
        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Long press to reset steps", Toast.LENGTH_SHORT).show();

            }
        });

        steps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                previewsTotalSteps = totalSteps;
                steps.setText("0");
                saveData();
                return true;
            }
        });
    }


    private void saveData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key1", String.valueOf(previewsTotalSteps));
        editor.apply();
    }


    private void loadData() {
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        int savedNumber = (int) sharedPref.getFloat("key1", 0f);
        previewsTotalSteps = savedNumber;
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}