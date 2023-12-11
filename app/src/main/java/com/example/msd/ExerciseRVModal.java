package com.example.msd;

public class ExerciseRVModal {
    private String exerciseName;
    private String exerciseDescription;
    private int imgResId; // Changed from String to int
    private int calories, time;

    public ExerciseRVModal(String exerciseName, String exerciseDescription, int imgResId, int calories, int time) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.imgResId = imgResId; // Changed from String to int
        this.calories = calories;
        this.time = time;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public int getImgResId() { // Changed return type to int
        return imgResId;
    }

    public void setImgResId(int imgResId) { // Changed parameter type to int
        this.imgResId = imgResId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
