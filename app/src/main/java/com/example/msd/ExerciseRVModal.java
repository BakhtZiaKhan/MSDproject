package com.example.msd;

// This class represents the data model for each exercise.
public class ExerciseRVModal {
    // Fields to store exercise data.
    private String exerciseName;
    private String exerciseDescription;
    private int imgResId; // Changed from String to hold resource ID of the image
    private int calories, time; // Calories burned and time duration of the exercise

    // Constructor to create an instance of ExerciseRVModal.
    public ExerciseRVModal(String exerciseName, String exerciseDescription, int imgResId, int calories, int time) {
        this.exerciseName = exerciseName;

        this.time = time;
    }

    // Getter and setter methods for exercise name.
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    // Getter and setter for time.
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
}
