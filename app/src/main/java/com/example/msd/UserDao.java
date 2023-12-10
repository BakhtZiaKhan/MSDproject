package com.example.msd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 1")
    User getLastUser();

    // Method to check if a user with a given username exists
    @Query("SELECT * FROM user_table WHERE username = :username")
    User findUserByUsername(String username);

    // Method to authenticate a user by username and password
    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);
}
