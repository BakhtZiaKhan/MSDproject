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

    // Add this method to get the last user
    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 1") // Assuming there's an 'id' field
    User getLastUser();
}
