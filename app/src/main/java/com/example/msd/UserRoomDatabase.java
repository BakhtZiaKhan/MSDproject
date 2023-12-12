package com.example.msd;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

// Annotation to define this class as a Room Database with a single entity (User) and a version number.
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    // Abstract method to get the UserDao interface for database operations.
    public abstract UserDao userDao();

    // Singleton instance of the database.
    private static volatile UserRoomDatabase INSTANCE;


    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                // Double-check if INSTANCE is still null to avoid multiple initializations.
                if (INSTANCE == null) {
                    // Creating the database instance using Room's database builder.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserRoomDatabase.class, "user_database")
                            // Handle migrations with a destructive strategy.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
