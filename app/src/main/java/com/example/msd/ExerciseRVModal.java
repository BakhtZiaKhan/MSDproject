package com.example.msd;

// This class represents the data model for each exercise.
// Reference: The following code is from https://www.geeksforgeeks.org/videos/how-to-create-health-fitness-application-in-android/
public class ExerciseRVModal {
    // Fields to store exercise data.
    private String exerciseName;
    private String exerciseDescription;
    private int imgResId; // Changed from String to hold resource ID of the image
    private int calories, time; // Calories burned and time duration of the exercise

    // Constructor to create an instance of ExerciseRVModal.

    public ExerciseRVModal(String exerciseName, String exerciseDescription, int imgResId, int calories, int time) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.imgResId = imgResId; // Changed from String to int
        this.calories = calories;
        this.time = time;
    }


// End of reference
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


    public int getImgResId() { // Changed return type to int
        return imgResId;
    }

    public void setImgResId(int imgResId) { // Changed parameter type to int
        this.imgResId = imgResId;
    }
}
