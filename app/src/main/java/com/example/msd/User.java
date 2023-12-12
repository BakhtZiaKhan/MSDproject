package com.example.msd;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Defining the User entity for the Room database.
// Reference: Code is based of  https://www.geeksforgeeks.org/how-to-perform-crud-operations-in-room-database-in-android/
@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id; // Unique ID for each user, auto-generated.
    private String username; // User's username.
    private float weight; // User's weight.
    private float height; // User's height.
    private String password; // User's password.

    // Constructor to create a new User instance.
    public User(String username, float weight, float height, String password) {
        this.username = username;
        this.weight = weight;
        this.height = height;
        this.password = password;
    }
    // End of Reference

    // Getters and setters for each field.
    // These methods allow for reading and updating the User's information.

    // Getter and setter for ID.
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for Username.
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for Weight.
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    // Getter and setter for Height.
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    // Getter and setter for Password.
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
