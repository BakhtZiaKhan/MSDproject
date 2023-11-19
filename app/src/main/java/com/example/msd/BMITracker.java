package com.example.msd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class BMITracker extends AppCompatActivity {

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tracker);

        button2 = (ImageButton) findViewById(R.id.button_2id);
        button1 = (ImageButton) findViewById(R.id.button_1id);
        button3 = (ImageButton) findViewById(R.id.button_3id);

        EditText editTextWeight = findViewById(R.id.weight);
        EditText editTextHeight = findViewById(R.id.height);
        Button button = findViewById(R.id.btnSubmit);
        TextView textView = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float height = Float.parseFloat(String.valueOf(editTextHeight.getText())) / 100;
                float weight = Float.parseFloat(String.valueOf(editTextWeight.getText()));
                float bmi = weight / (height * height);
                textView.setText(String.valueOf(bmi));
            }

        });

        button1.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(BMITracker.this, BMITracker.class);
                                           startActivity(intent);

                                       }
                                   }

        );




        button2.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(BMITracker.this,MainActivity .class);
                                           startActivity(intent);

                                       }
                                   }

        );

        button3.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View view) {

                                           Intent intent = new Intent(BMITracker.this, ExerciseActivity.class);
                                           startActivity(intent);

                                       }
                                   }

        );



    }

}