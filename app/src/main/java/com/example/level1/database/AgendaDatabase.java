package com.example.level1.database;

import static com.example.level1.database.DatabaseMigrations.ALL_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.level1.model.Student;

@Database(entities = {Student.class}, version = 3, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String DB_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract StudentDAO getRoomDAO();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(ALL_MIGRATIONS)
                    .build();
        }
        return instance;
    }
}
