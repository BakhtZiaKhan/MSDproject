package com.example.msd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

// Data Access Object (DAO) interface for the User entity.
// Reference: Code used from https://www.geeksforgeeks.org/how-to-perform-crud-operations-in-room-database-in-android/
@Dao
public interface UserDao {

    // Method to insert a new user into the database.
    @Insert
    void insertUser(User user);

    // Method to retrieve all users from the database.
    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();
    // End of reference

    // Method to get the most recently added user.
    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 1")
    User getLastUser();

    // Method to find a user by their username.
    // Useful for checking if a user already exists during registration.
    @Query("SELECT * FROM user_table WHERE username = :username")
    User findUserByUsername(String username);

    // Method for user authentication.
    // Checks if a user exists with the given username and password.
    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);
}
